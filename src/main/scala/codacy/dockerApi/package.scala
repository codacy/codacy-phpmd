package codacy

import java.nio.file.Path

import play.api.data.validation.ValidationError
import play.api.libs.json.Reads.StringReads
import play.api.libs.json._

import scala.util.Try

package object dockerApi {
  type Tool = (Path,Seq[PatternDef],Set[PatternSpec]) => Try[Iterable[Result]]

  class PatternId(       val value:String) extends AnyVal{ override def toString = value.toString }
  class SourcePath(      val value:String) extends AnyVal{ override def toString = value.toString }
  class ResultMessage(   val value:String) extends AnyVal{ override def toString = value.toString }
  class ResultLine(      val value:Int)    extends AnyVal{ override def toString = value.toString }
  class ToolName(        val value:String) extends AnyVal{ override def toString = value.toString }
  class ParameterName(   val value:String) extends AnyVal{ override def toString = value.toString }

  object PatternId{        def apply(v:String): PatternId        = new PatternId(v)       }
  object SourcePath{       def apply(v:String): SourcePath       = new SourcePath(v)      }
  object ResultMessage{    def apply(v:String): ResultMessage    = new ResultMessage(v)   }
  object ResultLine{       def apply(v:Int):    ResultLine       = new ResultLine(v)      }
  object ToolName{         def apply(v:String): ToolName         = new ToolName(v)        }
  object ParameterName{    def apply(v:String): ParameterName    = new ParameterName(v)   }

  case class ParameterDef(name:ParameterName,value:JsValue)
  case class PatternDef(patternId: PatternId, parameters:Option[Set[ParameterDef]])
  case class ToolConfig(name:ToolName, patterns:Seq[PatternDef])
  case class FullConfig(tools:Set[ToolConfig])

  //there are other fields like name and description but i don't care about them inside the tool
  case class ParameterSpec(name:ParameterName, default:JsValue)
  case class PatternSpec(patternId: PatternId, parameters:Option[Set[ParameterSpec]])
  case class Spec(name:ToolName,patterns:Set[PatternSpec])

  case class Result(filename:SourcePath,message:ResultMessage,patternId:PatternId,line: ResultLine)

  private[this] implicit val r08 = StringReads.map( ParameterName.apply )
  private[this] implicit val r05 = StringReads.map( PatternId.apply )
  private[this] implicit val r04 = StringReads.map( ToolName.apply )

  implicit lazy val specReader: Reads[Spec] = {
    implicit val r11 = Json.reads[ParameterSpec]
    implicit val r10 = Json.reads[PatternSpec]
    Json.reads[Spec]
  }

  implicit def configReader(implicit spec:Spec): Reads[FullConfig] = {
    implicit val r06 = Json.reads[ParameterDef]
    implicit val r02 = Json.reads[PatternDef].flatMap{ case pattern =>
      Reads((_:JsValue) =>
        if (spec.patterns.exists(_.patternId == pattern.patternId)) JsSuccess(pattern)
        else JsError(s"don't know this pattern: ${pattern.patternId}")
      )
    }
    implicit val r01 = Json.reads[ToolConfig].
      filter(ValidationError("no patterns selected"))(_.patterns.nonEmpty)

    Json.reads[FullConfig]
  }

  implicit lazy val writer: Writes[Result] = {
    implicit val r3 = Writes((v:ResultLine)    => Json.toJson(v.value))
    implicit val r2 = Writes((v:PatternId)     => Json.toJson(v.value))
    implicit val r1 = Writes((v:ResultMessage) => Json.toJson(v.value))
    implicit val r0 = Writes((v:SourcePath)    => Json.toJson(v.value))
    Json.writes[Result]
  }

}
