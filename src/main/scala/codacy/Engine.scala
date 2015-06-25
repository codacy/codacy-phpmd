package codacy

import codacy.jsHint.Tool
import play.api.libs.json.Json
import scala.util.{Failure, Success}
import dockerApi._

object Engine extends Environment{

  val toolName = ToolName("jsHint")

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

/*object SampleConfig{

  def fromCfg(toolConfig: ToolConfig) = {
    Tool(sourcePath,toolConfig.patterns,Set(new IgnorePath("/tmp/test.js"))).map(_.map(pathCorrected => println(Json.stringify(Json.toJson(pathCorrected)))).toList)
  }
  import codacy.jsHint.JsHintRule

  val prefix = "$minus"
  val allPats = JsHintRule.values.map{ case v =>
    val rawId = v.toString
    val id = if(rawId.startsWith(prefix)) s"-${rawId.drop(prefix.length)}" else rawId
    PatternDef(
      ruleId = RuleId(id),
      parameters = Set.empty
    )
  }

  val value = Config(
    tools = Set(ToolConfig(
      name = Engine.toolName,
      patterns = allPats.toSeq
    )),
    ignores = Set(new IgnorePath("/tmp/test.js"))
  )

  def apply() = value
}

*/
