package application

import library._

object AnalysePageObjet extends AnalysePage {
  override val objFiltrageHtml: FiltrageHtml = FiltrageHTML
  override val objFiltrageUrls: FiltrageURLs = FiltrageURL

  /** A partir d’une URL de requete sur le site de reference et d’une expression
    * exp, retourne une liste de pages issues de la requeete et satisfaisant
    * l’expression.
    * @param url
    *   l’URL de la requ^ete sur le site de r´ef´erence
    * @param exp
    *   l’expression `a v´erifier sur les pages trouv´ees
    * @return
    *   la liste des couples (titre,ref) o`u ref est l’URL d’une page
    *   satisfaisant l’expression et titre est son titre.
    */
  override def resultats(
      url: String,
      exp: Expression
  ): List[(String, String)] = {
    val html: Html = urlToHtml(url)
    val lUrls: List[String] = htmlTolUrls(html)
    val lCoupleUrlHtml: List[(String, Html)] = lUrlsToHtmls(lUrls)
    val lRequeteURLsHtml: List[(String, Html)] =
      filtrerPages(lCoupleUrlHtml, exp)
    var lRequeteTitreURLs: List[(String, String)] = extractTitlesAndUrls(
      lRequeteURLsHtml
    )
    lRequeteTitreURLs
  }

  /** A partir d'une URL revoie la page Html associée
    * @param url
    *   l'URL de la requête sur le site de référence
    */
  def urlToHtml(url: String): Html = {
    OutilsWebObjet.obtenirHtml(url)
  }

  /** A partir d'une page Html, renvoie une liste d'urls correspondant a
    * l'annonce
    * @param Html
    *   la page Html associée au site de référence
    */
  def htmlTolUrls(page: Html): List[String] = {
    FiltrageURL.filtreAnnonce(page)
  }

  /** A partir d'une liste d'URLs, revoie une liste de couple (URLs page HTML)
    * @param lurls
    *   Liste d'urls
    */
  def lUrlsToHtmls(lurls: List[String]): List[(String, Html)] = {
    var lUrlsHtmls: List[(String, Html)] = List()
    for (i <- lurls) {
      lUrlsHtmls = (i, OutilsWebObjet.obtenirHtml(i)) :: lUrlsHtmls
    }
    lUrlsHtmls
  }

  /** A partir d'une liste de couple (URL, page HTML) revoie la liste de couple
    * (URL,page HTML) filtrée qui répond à la requète
    * @param lurlsHtml
    *   La liste de couple référence
    * @param exp
    *   l'expretion correspondente a la requète
    */
  def filtrerPages(
      pages: List[(String, Html)],
      expression: Expression
  ): List[(String, Html)] = {
    pages.filter { case (url, html) =>
      FiltrageHTML.filtreHtml(html, expression)
    }
  }

  /** A partir d'un HTML, renvoie le titre de la page
    * @param h
    *   la page HTML
    */
  def obtenirTDans(h: Html): String = {
    def loop(html: Html): Option[String] = {
      html match {
        case Tag("title", _, List(Texte(title))) => Some(title)
        case Tag(_, _, children) =>
          children.flatMap(loop).headOption
        case _ => None
      }
    }

    loop(h).getOrElse(
      throw new NoSuchElementException("Aucun titre trouvé.")
    )
  }

  def extractTitlesAndUrls(
      htmlList: List[(String, Html)]
  ): List[(String, String)] = {
    htmlList.flatMap { case (url, html) =>
      try {
        Some((obtenirTDans(html), url))
      } catch {
        case _: NoSuchElementException => None
      }
    }
  }

}
