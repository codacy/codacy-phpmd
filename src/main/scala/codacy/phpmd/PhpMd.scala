package codacy.phpmd

import java.io.File
import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Path, Paths, StandardOpenOption}

import codacy.dockerApi._
import codacy.dockerApi.utils.CommandRunner
import play.api.libs.json.{JsString, Json}

import scala.util.{Failure, Success, Try}
import scala.xml._

object PhpMd extends Tool {

  lazy val configFileNames = Set("codesize.xml")

  def apply(path: Path, patternDefs: Option[List[PatternDef]], files: Option[Set[Path]])(implicit spec: Spec): Try[List[Result]] = {
    lazy val nativeConfigParam = {
      configFileNames.map( fn => Try( better.files.File(path) / fn ) ).collectFirst{ case Success(file) if file.isRegularFile =>
        file
      }.map(_.toJava.getAbsolutePath)
    }

    val patternConfigParam = {
      patternDefs.map { case patterns => Try(configFromPatterns(patterns)).flatMap(fileForConfig).map(_.toAbsolutePath.toString) }
    }

    val cfgParam = patternConfigParam match{
      case Some(Success(config)) => Success(config)
      case None => Success(nativeConfigParam.getOrElse(defaultRulesPath))
      case Some(f) => f
    }

    cfgParam.flatMap{ case configPath =>
      val filesPaths = files.map(_.map(_.toString).mkString(",")).getOrElse(path.toString)
      val cmd = List("phpmd", filesPaths, "xml", configPath)

      CommandRunner.exec(cmd) match {
        case Right(result) =>
          outputParsed(path, result.stdout.mkString)
        case Left(failure) =>
          Failure(failure)
      }
    }
  }

  private[this] lazy val defaultRulesPath = List("codesize", "cleancode", "controversial", "design", "unusedcode", "naming").
    map { case category =>
      s"rulesets/$category.xml"
    }.mkString(",")

  private[this] def xmlLocation(ruleName: String, ruleSet: String) = {
    val rsPart = ruleSet.stripSuffix("Rules").replaceAll(" ", "").toLowerCase
    s"rulesets-$rsPart.xml-$ruleName"
  }

  private[this] def patternIdByRuleNameAndRuleSet(ruleName: String, ruleSet: String)(implicit spec: Spec): Option[PatternId] = {
    spec.patterns.collectFirst { case pattern if pattern.patternId.value == xmlLocation(ruleName, ruleSet) =>
      pattern.patternId
    }
  }

  private[this] def relativizeToolOutputPath(basePath:Path, path: String): SourcePath = {
    SourcePath(basePath.relativize(Paths.get(path)).toString)
  }

  private[this] def outputParsed(basePath:Path, output: String)(implicit spec: Spec): Try[List[_ <: Result]] = {
    Try(XML.loadString(output)).map { case outputXml =>

      val issues = (outputXml \ "file").flatMap { case file =>
        List(file \@ "name").collect { case fname if fname.nonEmpty =>
          relativizeToolOutputPath(basePath, fname)
        }.flatMap { case filename =>
          (file \ "violation").flatMap { case violation =>
            patternIdByRuleNameAndRuleSet(
              ruleName = violation \@ "rule",
              ruleSet = violation \@ "ruleset"
            ).flatMap { case patternId =>
              Try(
                Issue(
                  filename = filename,
                  message = ResultMessage(violation.text.trim),
                  patternId = patternId,
                  line = ResultLine((violation \@ "beginline").toInt)
                )
              ).toOption
            }
          }
        }
      } //.toSet

      val errors = (outputXml \ "error").map { case error =>
        val path = relativizeToolOutputPath(basePath, error \@ "filename")
        val message = Option((error \@ "msg")).collect { case msg if msg.nonEmpty => ErrorMessage(msg) }
        FileError(path, message)
      }

      (issues ++ errors).toList

    }
  }

  private[this] def toXmlProperties(parameterDef: ParameterDef): Elem = {
    val stringValue = parameterDef.value match {
      case JsString(value) => value
      case other => Json.stringify(other)
    }
      <property name={parameterDef.name.value} value={stringValue}/>
  }

  private[this] def toXmlRule(patternDef: PatternDef)(implicit spec: Spec): Elem = {
    //get all default parameters and replace the ones supplied
    val defaultParams = defaultParametersFor(patternDef)
    val suppliedParams = patternDef.parameters.getOrElse(Set.empty)
    val allParams = defaultParams.filterNot { case param => suppliedParams.map(_.name).contains(param.name) } ++ suppliedParams
    val properties = allParams.toList.map(toXmlProperties)
    val xmlLocation = patternDef.patternId.value.replaceAll("-", "/")

    <rule ref={xmlLocation}>
      <properties>
        {properties}
      </properties>
    </rule>
  }

  private[this] def defaultParametersFor(patternDef: PatternDef)(implicit spec: Spec): Set[ParameterDef] = {
    spec.patterns.collectFirst {
      case patternSpec if patternSpec.patternId == patternDef.patternId =>
        patternSpec.parameters.map(_.map { case parameter =>
          ParameterDef(parameter.name, parameter.default)
        })
    } match {
      case Some(Some(params)) => params
      case _ => Set.empty[ParameterDef]
    }
  }

  private[this] def configFromPatterns(patterns: List[PatternDef])(implicit spec: Spec): Elem =
    <ruleset name="PHPMD rule set"
             xmlns="http://pmd.sf.net/ruleset/1.0.0"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://pmd.sf.net/ruleset/1.0.0 http://pmd.sf.net/ruleset_xml_schema.xsd"
             xsi:noNamespaceSchemaLocation=" http://pmd.sf.net/ruleset_xml_schema.xsd">
      {patterns.map(toXmlRule)}
    </ruleset>

  private[this] def fileForConfig(config: Elem) = tmpfile(config.toString())

  private[this] def tmpfile(content: String, prefix: String = "ruleset", suffix: String = ".xml"): Try[Path] = {
    Try(Files.write(
      Files.createTempFile(prefix, suffix),
      content.getBytes(StandardCharsets.UTF_8),
      StandardOpenOption.CREATE
    ))
  }
}
