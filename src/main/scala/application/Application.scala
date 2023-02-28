package application

import library._
import java.io.FileWriter

object Application extends App {
  // val exp: Expression = ParserExpression.lireExpression
  val exp: Expression = Et(Mot("peugeot"), Mot("308"))
  val lStringInfo: List[String] = expressionToStringList(exp)
  var lTiURL: List[(String, String)] = List()

  def expressionToStringList(expression: Expression): List[String] =
    expression match {
      case Mot(w) => List(w)
      case Et(e1, e2) => {
        for (s1 <- expressionToStringList(e1); s2 <- expressionToStringList(e2))
          yield s1 + "+" + s2
      }
      case Ou(e1, e2) =>
        expressionToStringList(e1) ++ expressionToStringList(e2)
    }

  for (i <- lStringInfo) {
    lTiURL = (AnalysePageObjet.resultats(
      ("https://search.vivastreet.com/annonces/fr?lb=new&search=1&start_field=1&keywords=" + i),
      exp
    )) ++ lTiURL
  }

  def titreTxt(exp: Expression): String = {
    exp match {
      case Mot(w)     => w
      case Et(e1, e2) => s"(${titreTxt(e1)} et ${titreTxt(e2)})"
      case Ou(e1, e2) => s"(${titreTxt(e1)} ou ${titreTxt(e2)})"
    }
  }

  val file = new FileWriter(titreTxt(exp) + ".html")
  try {
    file.write(HTMLToString.traduire(Resultat.resultatVersHtml(lTiURL)))
  } finally file.close()
  for (i <- lTiURL) println(i)
  print(file)

}
