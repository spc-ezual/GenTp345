package application

import library._

object FiltrageURL extends FiltrageURLs {

  /** A partir d’un document Html h, rend la liste des URLs accessibles `a
    * partir de h (ces URLs sont des hyperliens h) tels que ces URLs sont tous
    * des URLs d’annonces du site de r´ef´erence
    * @param h
    *   le document Html
    * @return
    *   la liste des URLs d’annonces contenues dans h
    */

  def filtreAnnonce(h: Html): List[String] = {
    var liens: List[String] = liensPages(h)
    var newLiens: List[String] = List()
    for (lien <- liens) {
      if (lien.length>9&&lien.substring(lien.length()-9).matches("^\\p{Digit}+$")) {
        newLiens = newLiens ++ List(lien)
      }
    }
    newLiens
  }

  /** A partir d’un document Html h, rend la liste des URLs accessibles `a
    * partir de h (ces URLs sont des hyperliens h) tels que ces URLs sont tous
    * des URLs
    * @param h
    *   le document html
    * @return
    *   la liste des URLs du document h
    */
  def liensPages(html: Html): List[String] = html match {
    case Tag(_, attributes, children) =>
      val links = attributes.collect { case ("href", url) => url }
      val images = attributes.collect { case ("src", url) => url }
      (links ++ images) ++ children.flatMap(liensPages)
    case Texte(_) => List.empty
  }

}
