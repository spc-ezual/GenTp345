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
      (for (i <- l) yield Tag("li", List(), List(Tag("a", List(("href", i._2)), List(Texte(i._1))))))
    val page = Tag(
      "html",
      List(),
      List(
        Tag(
          "head",
          List(),
          List(
            Tag("meta", List(("charset", "utf-8")), List()),
            Tag("title", List(), List(Texte("Résultat"))),
            Tag("style", List(), List(Texte("""
              body {
                margin: 0;
                padding: 0;
                font-family: Arial, sans-serif;
                background-color: #000000;
              }
              header {
                background-color: #ff0f0f;
                color: white;
                text-align: center;
                padding: 1em;
              }
              h1 {
                margin: 0;
                font-size: 2.5em;
              }
              #main {
                max-width: 600px;
                margin: 0 auto;
                padding: 2em;
              }
              ul {
                list-style-type: none;
                margin: 0;
                padding: 0;
              }
              li {
                margin: 1em 0;
              }
              a {
                color: #1a75ff;
                text-decoration: none;
                border-bottom: 1px solid #1a75ff;
              }
              a:hover {
                background-color: #1a75ff;
                color: white;
              }
            """)))
          )
        ),
        Tag("body", List(), List(
          Tag("header", List(), List(Tag("h1", List(), List(Texte("Résultat de la recherche"))))),
          Tag("div", List(("id", "main")), List(Tag("ul", List(), liste)))
        ))
      )
    )
    return page
  }

}
