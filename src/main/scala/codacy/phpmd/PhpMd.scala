package codacy.phpmd

import java.nio.charset.StandardCharsets
import java.nio.file.{StandardOpenOption, Files, Path}

import codacy.dockerApi._
import play.api.libs.json.{Json}
import scala.xml._

import scala.sys.process._
import scala.util.{Try,Properties}

private[phpmd] object RuleType extends Enumeration {
  val CleanCode, Codesize, Controversial, Design, UnusedCode, Naming = Value

  def xmlFile(level: RuleType.Value): String =
    s"rulesets/${level.toString.toLowerCase()}.xml/"
}

private[phpmd] case class Rule(ruleName: RuleName, ruleType: RuleType.Value){
  lazy val ruleId: RuleId = RuleId(s"${RuleType.xmlFile(ruleType)}${ruleName}")
}

private[phpmd] object Rule {

  private[this] val rules = List(
    "BooleanArgumentFlag",
    "ElseExpression",
    "StaticAccess"
  ).map( n => Rule(RuleName(n),RuleType.CleanCode)) ++ List(
    "CyclomaticComplexity",
    "NPathComplexity",
    "ExcessiveMethodLength",
    "ExcessiveClassLength",
    "ExcessiveParameterList",
    "ExcessivePublicCount",
    "TooManyFields",
    "TooManyMethods",
    "ExcessiveClassComplexity"
  ).map( n => Rule(RuleName(n),RuleType.Codesize)) ++ List(
    "Superglobals",
    "CamelCaseClassName",
    "CamelCasePropertyName",
    "CamelCaseMethodName",
    "CamelCaseParameterName",
    "CamelCaseVariableName"
  ).map( n => Rule(RuleName(n),RuleType.Controversial)) ++ List(
    "ExitExpression",
    "EvalExpression",
    "GotoStatement",
    "NumberOfChildren",
    "DepthOfInheritance",
    "CouplingBetweenObjects"
  ).map( n => Rule(RuleName(n),RuleType.Design)) ++ List(
    "UnusedPrivateField",
    "UnusedLocalVariable",
    "UnusedPrivateMethod",
    "UnusedFormalParameter"
  ).map( n => Rule(RuleName(n),RuleType.UnusedCode)) ++ List(
    "ShortVariable",
    "LongVariable",
    "ShortMethodName",
    "ConstructorWithNameAsEnclosingClass",
    "ConstantNamingConventions",
    "BooleanGetMethodName"
  ).map( n => Rule(RuleName(n),RuleType.Naming))

  def ruleIdbyPatternId(patternId: PatternId): Option[RuleId] = {
    rules.find(_.ruleName.value == patternId.value).map(_.ruleId)
  }

  def patternIdbyRuleName(ruleName: RuleName): Option[PatternId] = {
    rules.find(_.ruleName.value == ruleName.value).map{ case rule => PatternId(rule.ruleName.value) }
  }

}

object PhpMd extends Tool{

  override def apply(path: Path, patternDefs: Seq[PatternDef],patternSpecs: Set[PatternSpec]): Try[Iterable[Result]] = {
    Try(configFromPatterns(patternDefs)(patternSpecs)).flatMap{ case config =>

      fileForConfig(config).flatMap{ case configFile =>

        val configPath = configFile.toAbsolutePath().toString
        val cmd = s"phpmd $path xml $configPath".split(" ").toList

        Try(cmd.lineStream_!).map{ case output => outputParsed(output.mkString) }
      }
    }
  }

  //val lineEnd   = (violation \ "@endline").toString().toInt
  //val ruleSet   = (violation \ "@ruleset").toString()

  private[this] def outputParsed(output:String): Set[Result] = {
    Try(XML.loadString(output)).map{ case elem =>
      (elem \ "file").flatMap{ case file =>
        (file \ "violation").flatMap{ case violation =>
          Try{
            val ruleName = RuleName((violation \@ "rule"))
            Rule.patternIdbyRuleName(ruleName).map{ case patternId =>
              Result(
                filename = SourcePath((file \@ "name")),
                message = ResultMessage(violation.text),
                patternId = patternId,
                line = ResultLine((violation \@ "beginline").toInt)
              )
            }
          }.toOption.flatten[Result]
        }
      }
    }.map(_.toSet).getOrElse(Set.empty[Result])
  }

  private[this] def toXmlProperties(parameterDef:ParameterDef): Elem = {
    val name:String = parameterDef.name.value
    val value: String = Json.stringify(parameterDef.value)
    <property name={name} value={value} />
  }

  private[this] def toXmlRule(patternDef: PatternDef)(implicit spec:Iterable[PatternSpec]): Option[Elem] = {
    //get all default parameters and replace the ones supplied
    lazy val defaultParams = defaultParametersFor(patternDef)
    lazy val suppliedParams = patternDef.parameters.getOrElse(Set.empty)
    lazy val allParams = defaultParams.filterNot{ case param => suppliedParams.map(_.name).contains(param.name) } ++ suppliedParams
    lazy val properties = allParams.toSeq.map(toXmlProperties)

    Rule.ruleIdbyPatternId(patternDef.patternId).map{ case name =>
      <rule ref={name.value}><properties>{properties}</properties></rule>
    }
  }

  private[this] def defaultParametersFor(patternDef: PatternDef)(implicit spec:Iterable[PatternSpec]):Set[ParameterDef] = {
    spec.collectFirst{
      case patternSpec if patternSpec.patternId == patternDef.patternId =>
        patternSpec.parameters.map(_.map{ case parameter =>
          ParameterDef( parameter.name, parameter.default )
      })
    } match{
      case Some(Some(params)) => params
      case _ => Set.empty[ParameterDef]
    }
  }

  private[this] def configFromPatterns(patterns:Seq[PatternDef])(implicit spec:Iterable[PatternSpec]): Elem = {
      <ruleset name="PHPMD rule set" xmlns="http://pmd.sf.net/ruleset/1.0.0"
               xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
               xsi:schemaLocation="http://pmd.sf.net/ruleset/1.0.0 http://pmd.sf.net/ruleset_xml_schema.xsd"
               xsi:noNamespaceSchemaLocation=" http://pmd.sf.net/ruleset_xml_schema.xsd">
        { patterns.flatMap( toXmlRule(_) ) }
      </ruleset>
  }

  private[this] def fileForConfig(config:Elem) = tmpfile(config.toString())

  private[this] def tmpfile(content:String,prefix:String="ruleset",suffix:String=".xml"): Try[Path] = {
    Try(Files.write(
      Files.createTempFile(prefix,suffix),
      content.getBytes(StandardCharsets.UTF_8),
      StandardOpenOption.CREATE
    ))
  }
}
