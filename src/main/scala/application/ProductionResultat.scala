package application

import library._

object Resultat extends ProductionResultat {

  /** A partir d’une liste de couples (titre,URL), produit un document Html, qui
    * liste les solutions sous la forme de liens cliquables
    * @param l
    *   la liste des couples solution (titre,URL)
    * @return
    *   le document Html listant les solutions
    */

  def resultatVersHtml(l: List[(String, String)]): Html = {
    val liste =
      (for (i <- l) yield Tag("a", List(("href", i._2)), List(Texte(i._1))))
    val page = Tag(
      "html",
      List(),
      List(
        Tag(
          "head",
          List(),
          List(
            Tag("meta", List(("charset", "utf-8")), List()),
            Tag("title", List(), List(Texte("Résultat")))
          )
        ),
        Tag("body", List(), List(Tag("center", List(), liste)))
      )
    )
    return page
  }

}
