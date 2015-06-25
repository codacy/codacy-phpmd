package codacy

import play.api.data.validation.ValidationError
import play.api.libs.json.Reads.StringReads
import play.api.libs.json.{JsValue, Writes, Json, Reads}

package object dockerApi {

  class IgnorePath(      val value:String) extends AnyVal
  class PatternId(       val value:String) extends AnyVal
  class SourcePath(      val value:String) extends AnyVal
  class ResultMessage(   val value:String) extends AnyVal
  class ResultLine(      val value:Int)    extends AnyVal
  class ToolName(        val value:String) extends AnyVal
  class ParameterName(   val value:String) extends AnyVal

  object PatternId{        def apply(v:String): PatternId        = new PatternId(v)       }
  object SourcePath{       def apply(v:String): SourcePath       = new SourcePath(v)      }
  object ResultMessage{    def apply(v:String): ResultMessage    = new ResultMessage(v)   }
  object ResultLine{       def apply(v:Int):    ResultLine       = new ResultLine(v)      }
  object ToolName{         def apply(v:String): ToolName         = new ToolName(v)        }
  object ParameterName{    def apply(v:String): ParameterName    = new ParameterName(v)   }
  object IgnorePath{       def apply(v:String): IgnorePath       = new IgnorePath(v)      }

  case class ParameterDef(name:ParameterName,value:JsValue)
  case class PatternDef(patternId: PatternId, parameters:Set[ParameterDef])
  case class ToolConfig(name:ToolName, patterns:Seq[PatternDef])
  case class Config(tools:Set[ToolConfig],ignores:Set[IgnorePath])
  case class Result(filename:SourcePath,message:ResultMessage,ruleId:PatternId,line: ResultLine)

  implicit lazy val reader: Reads[Config] = {
    implicit val r08 = StringReads.map( ParameterName.apply )
    implicit val r05 = StringReads.map( PatternId.apply )
    implicit val r04 = StringReads.map( ToolName.apply )
    implicit val r03 = StringReads.map( IgnorePath.apply )
    implicit val r06 = Json.reads[ParameterDef]
    implicit val r02 = Json.reads[PatternDef]
    implicit val r01 = Json.reads[ToolConfig].filter(ValidationError("no patterns selected"))(_.patterns.nonEmpty)
    implicit val r00 = Json.reads[Config]
    Json.reads[Config]
  }

  implicit lazy val writer: Writes[Result] = {
    implicit val r3 = Writes((v:ResultLine)    => Json.toJson(v.value))
    implicit val r2 = Writes((v:PatternId)     => Json.toJson(v.value))
    implicit val r1 = Writes((v:ResultMessage) => Json.toJson(v.value))
    implicit val r0 = Writes((v:SourcePath)    => Json.toJson(v.value))
    Json.writes[Result]
  }
}
