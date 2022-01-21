package codacy.phpmd

import scala.xml._

object ConfigFileSanitizer {

  def sanitize(content: String): String = {
    def loop(node: Elem): Elem = {
      node.copy(child = node.child.filter(isValid).map {
        case elem: Elem => loop(elem)
        case somethingElse => somethingElse
      })
    }
    val parsed = XML.loadString(content)
    val result = loop(parsed)
    val writer = new java.io.StringWriter()
    XML.write(writer, result, "UTF-8", xmlDecl = true, doctype = null)
    writer.toString()
  }

  private def isValid(elem: Node): Boolean = {
    if (elem.label == "rule") {
      val isNotCustomRule = (elem \ "@class").isEmpty && (elem \ "@file").isEmpty
      val doesntReferenceSrcDir = {
        val ref = elem \@ "ref"
        !ref.contains("..") && !ref.contains("/src")
      }
      isNotCustomRule && doesntReferenceSrcDir
    } else true
  }

}
