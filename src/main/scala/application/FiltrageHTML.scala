package application

import library._

object FiltrageHTML extends FiltrageHtml {
  def filtreHtml(h: Html, e: Expression): Boolean = {
    e match {
      case Et(e1, e2) => filtreHtml(h, e1) && filtreHtml(h, e2)
      case Ou(e1, e2) => filtreHtml(h, e1) || filtreHtml(h, e2)
      case Mot(w)     => contientMotCle(h, w)
    }
  }

  def contientMotCle(h: Html, a: String): Boolean = {
    h match {
      case Tag(_, _, children) => children.exists(contientMotCle(_, a))
      case Texte(content)      => content.contains(a)
    }
  }
}
