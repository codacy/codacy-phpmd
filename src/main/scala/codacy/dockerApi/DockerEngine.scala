package codacy.dockerApi

import play.api.libs.json.Json
import scala.util.{Failure, Success}

abstract class DockerEngine(Tool:Tool) extends DockerEnvironment{

  def main(args: Array[String]): Unit = {
    spec.flatMap{ implicit spec =>
      config.flatMap{ case config =>
        config.tools.collectFirst{ case toolConfig if toolConfig.name == spec.name =>
          Tool(sourcePath,toolConfig.patterns,spec.patterns)
        }.getOrElse( Failure(new Throwable(s"no config for ${spec.name}")) )
      }
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
