package codacy

import codacy.jshint.Tool
import play.api.libs.json.Json
import scala.util.{Failure, Success}
import dockerApi._

object Engine extends Environment{

  val toolName = ToolName("jshint")

  def main(args: Array[String]): Unit = {
    config.flatMap{ case config =>
      config.tools.collectFirst{ case toolConfig if toolConfig.name == toolName =>
        Tool(sourcePath,toolConfig.patterns)
      }.getOrElse( Failure(new Throwable(s"no config for $toolName")) )
    } match{
      case Success(results) =>
        val all = results.map{ case result =>
          val pathCorrected = result.copy(filename = pathStripped(result.filename))
          println(Json.stringify(Json.toJson(pathCorrected)))
        }.toList
        //println(s"found ${all.size} issues")
      case Failure(error) =>
        error.printStackTrace(Console.err)
        System.exit(1)
    }
  }

}