package codacy

import java.nio.file.{Path, Files, Paths}
import play.api.libs.json._
import scala.util.{Failure, Success, Try}
import dockerApi._

trait Environment{

  private[this] lazy val configFilePath = sourcePath.resolve(".codacy.json")

  lazy val config: Try[Config] = Try(Files.readAllBytes(configFilePath)).
    map( Json.parse ).flatMap(
      _.validate[Config].fold(
        error => Failure(new Throwable("error parsing config")),
        Success.apply
      )
    )

  lazy val sourcePath: Path = Paths.get("/src")

  def pathStripped(sourcePath:SourcePath) = SourcePath(sourcePath.value)
}
