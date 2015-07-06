package codacy.phpmd

import java.nio.charset.StandardCharsets
import java.nio.file.{StandardOpenOption, Files, Path}

import codacy.dockerApi._
import play.api.libs.json.Json
import scala.xml._
import scala.sys.process._
import scala.util.Try

object PhpMd extends Tool{

  def apply(path: Path, patternDefs: Seq[PatternDef])(implicit spec: Spec): Try[Iterable[Result]] = {
    Try(configFromPatterns(patternDefs)).flatMap{ case config =>

      fileForConfig(config).flatMap{ case configFile =>

        val configPath = configFile.toAbsolutePath().toString
        val cmd = s"phpmd $path xml $configPath".split(" ").toList
        Try(cmd.lineStream_!).map{ case output =>
          outputParsed(output.mkString)
        }
      }
    }
  }

  private[this] def xmlLocation(ruleName:String,ruleSet:String) = {
    val rsPart = ruleSet.dropRight("Rules".length).replaceAll(" ","").toLowerCase()
    s"rulesets-$rsPart.xml-$ruleName"
  }

  private[this] def patternIdByRuleNameAndRuleSet(ruleName: String, ruleSet:String)(implicit spec: Spec):Option[PatternId] = {
    spec.patterns.collectFirst{ case pattern if pattern.patternId.value == xmlLocation(ruleName,ruleSet) =>
      pattern.patternId
    }
  }

  private[this] def outputParsed(output:String)(implicit spec: Spec): Set[Result] = {
    Try(XML.loadString(output)).map{ case elem =>
      (elem \ "file").flatMap{ case file =>
        (file \ "violation").flatMap{ case violation =>
          Try{
            patternIdByRuleNameAndRuleSet(
              ruleName = violation \@ "rule",
              ruleSet = violation \@ "ruleset"
            ).map{ case patternId =>
              Result(
                filename = SourcePath((file \@ "name")),
                message = ResultMessage(violation.text.trim),
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
    <property name={ parameterDef.name.value } value={ Json.stringify(parameterDef.value) } />
  }

  private[this] def toXmlRule(patternDef: PatternDef)(implicit spec:Spec): Elem = {
    //get all default parameters and replace the ones supplied
    val defaultParams = defaultParametersFor(patternDef)
    val suppliedParams = patternDef.parameters.getOrElse(Set.empty)
    val allParams = defaultParams.filterNot{ case param => suppliedParams.map(_.name).contains(param.name) } ++ suppliedParams
    val properties = allParams.toSeq.map(toXmlProperties)
    val xmlLocation = patternDef.patternId.value.replaceAll("-","/")

    <rule ref={xmlLocation}><properties>{properties}</properties></rule>
  }

  private[this] def defaultParametersFor(patternDef: PatternDef)(implicit spec:Spec):Set[ParameterDef] = {
    spec.patterns.collectFirst{
      case patternSpec if patternSpec.patternId == patternDef.patternId =>
        patternSpec.parameters.map(_.map{ case parameter =>
          ParameterDef( parameter.name, parameter.default )
      })
    } match{
      case Some(Some(params)) => params
      case _ => Set.empty[ParameterDef]
    }
  }

  private[this] def configFromPatterns(patterns:Seq[PatternDef])(implicit spec:Spec): Elem =
    <ruleset name="PHPMD rule set"
             xmlns="http://pmd.sf.net/ruleset/1.0.0"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://pmd.sf.net/ruleset/1.0.0 http://pmd.sf.net/ruleset_xml_schema.xsd"
             xsi:noNamespaceSchemaLocation=" http://pmd.sf.net/ruleset_xml_schema.xsd">
      { patterns.map( toXmlRule(_) ) }
    </ruleset>

  private[this] def fileForConfig(config:Elem) = tmpfile(config.toString())

  private[this] def tmpfile(content:String,prefix:String="ruleset",suffix:String=".xml"): Try[Path] = {
    Try(Files.write(
      Files.createTempFile(prefix,suffix),
      content.getBytes(StandardCharsets.UTF_8),
      StandardOpenOption.CREATE
    ))
  }
}
