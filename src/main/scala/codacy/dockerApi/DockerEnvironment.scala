package codacy.dockerApi

import java.nio.file.{Files, Path, Paths}

import play.api.libs.json._

import scala.io.Source
import scala.util.{Properties, Failure, Success, Try}

trait DockerEnvironment{

  private[this] lazy val configFilePath = sourcePath.resolve(".codacy.json")

  def config(implicit spec: Spec): Try[Option[FullConfig]] = Try(Files.readAllBytes(configFilePath)).transform(
    raw => Try(Json.parse(raw)).flatMap(
      _.validate[FullConfig].fold(
        error => Failure(new Throwable(Json.stringify(JsError.toFlatJson(error)))),
        conf => Success(Option(conf))
      )),
    _ => Success(Option.empty[FullConfig])
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
}