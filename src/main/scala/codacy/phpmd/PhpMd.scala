package codacy.phpmd

import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Path, Paths, StandardOpenOption}

import better.files.File
import com.codacy.plugins.api.results.Result.{FileError, Issue}
import com.codacy.plugins.api.results.Tool.Specification
import com.codacy.plugins.api.results.{Parameter, Pattern, Result, Tool}
import com.codacy.plugins.api.{ErrorMessage, Options, Source}
import com.codacy.plugins.api.paramValueToJsValue
import com.codacy.tools.scala.seed.utils.CommandRunner
import play.api.libs.json.{JsString, Json}

import scala.util.{Failure, Properties, Success, Try}
import scala.xml._

object PhpMd extends Tool {

  lazy val configFileNames = Set("codesize.xml", "phpmd.xml")

  override def apply(source: Source.Directory,
                     configuration: Option[List[Pattern.Definition]],
                     files: Option[Set[Source.File]],
                     options: Map[Options.Key, Options.Value])(implicit specification: Specification): Try[List[Result]] = {
    lazy val nativeConfigParam = {
      configFileNames.map(fn => Try(better.files.File(source.path) / fn)).collectFirst { case Success(file) if file.isRegularFile =>
        file
      }.map(_.toJava.getAbsolutePath)
    }

    val patternConfigParam = {
      configuration.map { patterns => Try(configFromPatterns(patterns)).flatMap(fileForConfig).map(_.toAbsolutePath.toString) }
    }

    val cfgParam = patternConfigParam match {
      case Some(Success(config)) => Success(config)
      case None => Success(nativeConfigParam.getOrElse(defaultRulesPath))
      case Some(f) => f
    }

    cfgParam.flatMap { configPath =>
      val filesPaths = files.map(_.map(_.toString).mkString(",")).getOrElse(source.path)
      val cmd = List("phpmd", filesPaths, "xml", configPath)

      CommandRunner.exec(cmd, Option(File(source.path).toJava)) match {
        case Right(result) =>
          outputParsed(File(source.path).path, result.stdout.mkString) match {
            case s@Success(_) => s
            case Failure(e) =>
              val msg =
                s"""
                   |PHP Mess Detector exited with code ${result.exitCode}
                   |message: ${e.getMessage}
                   |stdout: ${result.stdout.mkString(Properties.lineSeparator)}
                   |stderr: ${result.stderr.mkString(Properties.lineSeparator)}
                """.stripMargin
              Failure(new Exception(msg))
          }
        case Left(failure) =>
          Failure(failure)
      }
    }
  }

  private[this] lazy val defaultRulesPath = List("codesize", "cleancode", "controversial", "design", "unusedcode", "naming").
    map { category =>
      s"rulesets/$category.xml"
    }.mkString(",")

  private[this] def xmlLocation(ruleName: String, ruleSet: String) = {
    val rsPart = ruleSet.stripSuffix("Rules").replaceAll(" ", "").toLowerCase
    s"rulesets-$rsPart.xml-$ruleName"
  }

  private[this] def patternIdByRuleNameAndRuleSet(ruleName: String, ruleSet: String)(implicit spec: Specification): Option[Pattern.Id] = {
    spec.patterns.collectFirst {
      case pattern if pattern.patternId.value == xmlLocation(ruleName, ruleSet) || pattern.patternId.value.endsWith(ruleName) =>
        pattern.patternId
    }
  }

  private[this] def relativizeToolOutputPath(basePath: Path, path: String): Source.File = {
    Source.File(basePath.relativize(Paths.get(path)).toString)
  }

  private[this] def outputParsed(basePath: Path, output: String)(implicit spec: Specification): Try[List[_ <: Result]] = {
    Try(XML.loadString(output)).map { outputXml =>

      val issues = (outputXml \ "file").flatMap { file =>
        List(file \@ "name").collect { case fname if fname.nonEmpty =>
          relativizeToolOutputPath(basePath, fname)
        }.flatMap { fileName =>
          (file \ "violation").flatMap { violation =>
            patternIdByRuleNameAndRuleSet(
              ruleName = violation \@ "rule",
              ruleSet = violation \@ "ruleset"
            ).flatMap { patternId =>
              Try(
                Issue(
                  file = fileName,
                  message = Result.Message(violation.text.trim),
                  patternId = patternId,
                  line = Source.Line((violation \@ "beginline").toInt)
                )
              ).toOption
            }
          }
        }
      }

      val errors = (outputXml \ "error").map { error =>
        val path = relativizeToolOutputPath(basePath, error \@ "filename")
        val message = Option(error \@ "msg").collect { case msg if msg.nonEmpty => ErrorMessage(msg) }
        FileError(path, message)
      }

      (issues ++ errors).toList

    }
  }

  private[this] def toXmlProperties(parameterDef: Parameter.Definition): Elem = {
    val stringValue = paramValueToJsValue(parameterDef.value) match {
      case JsString(value) => value
      case other => Json.stringify(other)
    }
      <property name={parameterDef.name.value} value={stringValue}/>
  }

  private[this] def toXmlRule(patternDef: Pattern.Definition)(implicit spec: Specification): Elem = {
    //get all default parameters and replace the ones supplied
    val defaultParams = defaultParametersFor(patternDef)
    val suppliedParams = patternDef.parameters.getOrElse(Set.empty)
    val allParams = defaultParams.filterNot { param => suppliedParams.map(_.name).contains(param.name) } ++ suppliedParams
    val properties = allParams.toList.map(toXmlProperties)
    val xmlLocation = patternDef.patternId.value.replaceAll("-", "/")

    <rule ref={xmlLocation}>
      <properties>
        {properties}
      </properties>
    </rule>
  }

  private[this] def defaultParametersFor(patternDef: Pattern.Definition)(implicit spec: Specification): Set[Parameter.Definition] = {
    spec.patterns.collectFirst {
      case patternSpec if patternSpec.patternId == patternDef.patternId =>
        patternSpec.parameters.map(_.map { parameter =>
          Parameter.Definition(parameter.name, parameter.default)
        })
    } match {
      case Some(Some(params)) => params
      case _ => Set.empty[Parameter.Definition]
    }
  }

  private[this] def configFromPatterns(patterns: List[Pattern.Definition])(implicit spec: Specification): Elem =
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
