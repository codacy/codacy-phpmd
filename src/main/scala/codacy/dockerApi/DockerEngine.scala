package codacy.dockerApi

import play.api.libs.json.Json
import scala.util.{Failure, Success}

abstract class DockerEngine(Tool:Tool) extends DockerEnvironment{

  def main(args: Array[String]): Unit = {
    spec.flatMap{ implicit spec =>
      config.flatMap{ case fullConfig =>
        fullConfig.tools.collectFirst{ case config if config.name == spec.name =>

          Tool(
            path = sourcePath,
            conf = config.patterns,
            files = fullConfig.files.map(_.map{ case path =>
              sourcePath.resolve(path.value)
            })
          )

        }.getOrElse(Failure(new Throwable(s"no config for ${spec.name} found")))
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
