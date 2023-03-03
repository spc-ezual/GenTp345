
import org.junit.Test
import org.junit.Assert._
import application.FiltrageURL._
import library._


class TestsFiltreU {

  @Test
  def Test1_filtreAnnonce(): Unit = {
    assert(filtreAnnonce(
      Tag("html", List(),
    List(
      Tag("head", List(),
        List(
          Tag("meta", List(("charset", "utf-8")), List()),
          Tag("title", List(), List(Texte("My Page"))))),
      Tag("body", List(), List(
        Tag("center", List(), List(
          Tag("a", List(("href", "https://search.vivastreet.com/annonces/fr?lb=new&search=1&start_field=1&keywords=voiture")),
            List(Texte("Lien"))),Texte("Test1")))))))
    ).length==0
    )
  }  
  @Test
  def Test2_filtreAnnonce(): Unit = {
    assert(filtreAnnonce(
      Tag("html", List(),
    List(
      Tag("head", List(),
        List(
          Tag("meta", List(("charset", "utf-8")), List()),
          Tag("title", List(), List(Texte("My Page"))))),
      Tag("body", List(), List(
        Tag("center", List(), List(
          Tag("a", List(("href", "https://www.vivastreet.com/immobilier-appartement/etranger-portugal/nouvel-appartement-de-3-chambres-avec-balcon---vila-franca-de---/313924134")),
            List(Texte("Lien"))),Texte("Test1")))))))
    ).length==1)
  }  
  @Test
  def Test3_filtreAnnonce(): Unit = {
    assert(filtreAnnonce(
      Tag("html", List(),
    List(
      Tag("head", List(),
        List(
          Tag("meta", List(("charset", "utf-8")), List()),
          Tag("title", List(), List(Texte("My Page"))))),
      Tag("body", List(), List(
        Tag("center", List(), List(
          Tag("a", List(),
            List(Texte("Lien"))),Texte("Test1")))))))
    ).length==0)
  }
    
@Test
  def Test1_liensPages(): Unit = {
    assert(liensPages(
      Tag("html", List(),
    List(
      Tag("head", List(),
        List(
          Tag("meta", List(("charset", "utf-8")), List()),
          Tag("title", List(), List(Texte("My Page"))))),
      Tag("body", List(), List(
        Tag("center", List(), List(
          Tag("a", List(("href", "https://search.vivastreet.com/annonces/fr?lb=new&search=1&start_field=1&keywords=voiture")),
            List(Texte("Lien"))),Texte("Test1")))))))
    ).length==1
    )
  }  
  @Test
  def Test2_liensPages(): Unit = {
    assert(liensPages(
      Tag("html", List(),
    List(
      Tag("head", List(),
        List(
          Tag("meta", List(("charset", "utf-8")), List()),
          Tag("title", List(), List(Texte("My Page"))))),
      Tag("body", List(), List(
        Tag("center", List(), List(
          Tag("a", List(("href", "https://www.vivastreet.com/immobilier-appartement/etranger-portugal/nouvel-appartement-de-3-chambres-avec-balcon---vila-franca-de---/313924134")),
            List(Texte("Lien"))),Texte("Test1")))))))
    ).length==1)
  }  
  @Test
  def Test3_liensPages(): Unit = {
    assert(liensPages(
      Tag("html", List(),
    List(
      Tag("head", List(),
        List(
          Tag("meta", List(("charset", "utf-8")), List()),
          Tag("title", List(), List(Texte("My Page"))))),
      Tag("body", List(), List(
        Tag("center", List(), List(
          Tag("a", List(),
            List(Texte("Lien"))),Texte("Test1")))))))
    ).length==0)
  }
}
