package codacy.scalameta

import java.nio.file.{Files, Path,Paths}
import codacy.dockerApi._
import scala.util.Try

import scala.meta._
import scala.meta.dialects.Scala211

object ScalaMeta extends Tool{


  override def apply(path: Path, patterns: Seq[PatternDef]): Try[Iterable[Result]] = {
    Try(recursiveFiles(path)).map{
      _.flatMap{
        case path if path.toString.endsWith(".scala") =>

         Try(path.toFile.parse[Source]).map{ case source:Tree =>
           matchesInTree(source)
         }.getOrElse(Seq.empty[Result])

        case _ => Seq.empty[Result]
      }
    }
  }


  //this might not be the best solution but it uses only built-in stuff
  private[this] def recursiveFiles(root:Path): Stream[Path] = {
    val javaS = Files.walk(root).iterator()
    def rec: Stream[Path] = if(javaS.hasNext) rec.#::(javaS.next()) else Stream.empty[Path]
    rec.#::(javaS.next())
  }

  private[this] def matchesInTree(tree:Tree): Iterable[Result] = {
    Seq.empty
  }



}
