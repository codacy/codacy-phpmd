package codacy

import codacy.jsHint.Tool
import play.api.libs.json.Json
import scala.util.{Failure, Success}
import dockerApi._

object Engine extends Environment{

  val toolName = ToolName("jshint")

  def main(args: Array[String]): Unit = config.flatMap{ case config =>
    config.tools.collectFirst{ case toolConfig if toolConfig.name == toolName =>
      Tool(sourcePath,toolConfig.patterns,config.ignores)
    }.getOrElse(
      Failure(new Throwable(s"no config for $toolName"))
    )
  } match{
    case Success(results) =>
      results.map{ case result =>
        val pathCorrected = result.copy(filename = pathStripped(result.filename))
        println(Json.stringify(Json.toJson(pathCorrected)))
      }.toList
    case Failure(error) =>
      Console.err.println(error.getMessage())
      System.exit(1)
  }
}