package application

import library._

object HTMLToString extends HtmlVersString {
  def traduire(html: Html): String = {
    html match {
      case Tag(name, attributes, children) =>
        val attrString = attributes.map { case (key, value) => s"""$key="$value"""" }.mkString(" ")
        val childrenString = children.map(traduire).mkString("\n")
        s"<$name $attrString>\n$childrenString\n</$name>\n"
      case Texte(content) =>
        content
    }
  }
}
