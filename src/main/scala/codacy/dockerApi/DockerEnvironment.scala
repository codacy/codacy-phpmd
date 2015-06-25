package codacy.dockerApi

import java.nio.file.{Files, Path, Paths}

import play.api.libs.json._

import scala.util.{Failure, Success, Try}

trait DockerEnvironment{

  private[this] lazy val configFilePath = sourcePath.resolve(".codacy.json")

  lazy val config: Try[Config] = Try(Files.readAllBytes(configFilePath)).
    map( Json.parse ).flatMap(
      _.validate[Config].fold(
        error => Failure(new Throwable(Json.stringify(JsError.toFlatJson(error)))),
        Success.apply
      )
    )

  private[this] lazy val srcPathRaw = "/src"
  lazy val sourcePath: Path = Paths.get(srcPathRaw)

  def pathStripped(sourcePath:SourcePath) = Option(sourcePath.value).collect{
    case p if p.startsWith(srcPathRaw) => SourcePath(p.drop(srcPathRaw.length))
  }.getOrElse(sourcePath)
}
