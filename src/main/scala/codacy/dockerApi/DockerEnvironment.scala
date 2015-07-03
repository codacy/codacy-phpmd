package codacy.dockerApi

import java.nio.file.{Files, Path, Paths}

import play.api.libs.json._

import scala.io.Source
import scala.util.{Properties, Failure, Success, Try}

trait DockerEnvironment{

  private[this] lazy val configFilePath = sourcePath.resolve(".codacy.json")

  def config(implicit spec: Spec): Try[ToolConfig] = Try(Files.readAllBytes(configFilePath)).
    map( Json.parse ).flatMap(
      _.validate[ToolConfig].fold(
        error => Failure(new Throwable(Json.stringify(JsError.toFlatJson(error)))),
        Success.apply
      )
    )

  lazy val spec: Try[Spec] = {
    val source = Try(Source.fromURL(getClass.getResource("/docs/patterns.json")))
    val result = source.flatMap{ case source =>
      val content = source.getLines().mkString(Properties.lineSeparator)
      Try(Json.parse(content)).flatMap(_.validate[Spec].fold(
        error => Failure(new Throwable(Json.stringify(JsError.toFlatJson(error)))),
        Success.apply
      ))
    }

    source.map(_.close())
    result
  }

  private[this] lazy val srcPathRaw = "/src"
  lazy val sourcePath: Path = Paths.get(srcPathRaw)

  def pathStripped(sourcePath:SourcePath) = Option(sourcePath.value).collect{
    case p if p.startsWith(srcPathRaw) => SourcePath(p.drop(srcPathRaw.length))
  }.getOrElse(sourcePath)
}