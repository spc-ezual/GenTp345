
import org.junit.Test
import org.junit.Assert._
import application.FiltrageHTML._
import library._


class TestsFiltreH {

  @Test
  def Test1_filtreHtml(): Unit = {
    assert(filtreHtml(Tag("html", List(),
    List(
      Tag("head", List(),
        List(
          Tag("meta", List(("charset", "utf-8")), List()),
          Tag("title", List(), List(Texte("My Page"))))),
      Tag("body", List(), List(
        Tag("center", List(), List(
          Tag("a", List(("href", "http://www.irisa.fr")),
            List(Texte("Lien"))),Texte("Test1"))))))),Mot("Test1")))
  }  
  @Test
  def Test2_filtreHtml(): Unit = {
    assert(!filtreHtml(Tag("html", List(),
    List(
      Tag("head", List(),
        List(
          Tag("meta", List(("charset", "utf-8")), List()),
          Tag("title", List(), List(Texte("My Page"))))),
      Tag("body", List(), List(
        Tag("center", List(), List(
          Tag("a", List(("href", "http://www.irisa.fr")),
            List(Texte("Lien"))),Texte("Test2"))))))),Mot("Test1")))
  }  
  @Test
  def Test3_filtreHtml(): Unit = {
    assert(filtreHtml(Tag("html", List(),
    List(
      Tag("head", List(),
        List(
          Tag("meta", List(("charset", "utf-8")), List()),
          Tag("title", List(), List(Texte("My Page"))))),
      Tag("body", List(), List(
        Tag("center", List(), List(
          Tag("a", List(("href", "http://www.irisa.fr")),
            List(Texte("Lien"))),Texte("voiture de luxe."))))))),Mot("luxe")))
  }
  @Test
  def Test4_filtreHtml(): Unit = {
    assert(filtreHtml(Tag("html", List(),
    List(
      Tag("head", List(),
        List(
          Tag("meta", List(("charset", "utf-8")), List()),
          Tag("title", List(), List(Texte("My Page"))))),
      Tag("body", List(), List(
        Tag("center", List(), List(
          Tag("a", List(("href", "http://www.irisa.fr")),
            List(Texte("Lien"))),Texte("Test1"))))))),Ou(Mot("Test1"),Mot("Test2"))))
  }  
  @Test
  def Test5_filtreHtml(): Unit = {
    assert(!filtreHtml(Tag("html", List(),
    List(
      Tag("head", List(),
        List(
          Tag("meta", List(("charset", "utf-8")), List()),
          Tag("title", List(), List(Texte("My Page"))))),
      Tag("body", List(), List(
        Tag("center", List(), List(
          Tag("a", List(("href", "http://www.irisa.fr")),
            List(Texte("Lien"))),Texte("Test2"))))))),Et(Mot("Test1"),Mot("Test2"))))
  }  
  @Test
  def Test6_filtreHtml(): Unit = {
    assert(filtreHtml(Tag("html", List(),
    List(
      Tag("head", List(),
        List(
          Tag("meta", List(("charset", "utf-8")), List()),
          Tag("title", List(), List(Texte("My Page"))))),
      Tag("body", List(), List(
        Tag("center", List(), List(
          Tag("a", List(("href", "http://www.irisa.fr")),
            List(Texte("Lien"))),Texte("voiture de luxe."))))))),Et(Mot("voiture"),Mot("luxe"))))
  }  

@Test
  def Test1_contientMotCle(): Unit = {
    assert(contientMotCle(Tag("html", List(),
    List(
      Tag("head", List(),
        List(
          Tag("meta", List(("charset", "utf-8")), List()),
          Tag("title", List(), List(Texte("My Page"))))),
      Tag("body", List(), List(
        Tag("center", List(), List(
          Tag("a", List(("href", "http://www.irisa.fr")),
            List(Texte("Lien"))),Texte("Test1 teste0 et 5"))))))),"Test1"))
  }  
  @Test
  def Test2_contientMotCle(): Unit = {
    assert(!contientMotCle(Tag("html", List(),
    List(
      Tag("head", List(),
        List(
          Tag("meta", List(("charset", "utf-8")), List()),
          Tag("title", List(), List(Texte("My Page"))))),
      Tag("body", List(), List(
        Tag("center", List(), List(
          Tag("a", List(("href", "http://www.irisa.fr")),
            List(Texte("Lien"))),Texte("Test21 et testswd40"))))))),"Test1"))
  }  
  @Test
  def Test3_contientMotCle(): Unit = {
    assert(!contientMotCle(Tag("html", List(),
    List(
      Tag("head", List(),
        List(
          Tag("meta", List(("charset", "utf-8")), List()),
          Tag("title", List(), List(Texte("My Page"))))),
      Tag("body", List(), List(
        Tag("center", List(), List(
          Tag("a", List(("href", "http://www.irisa.fr")),
            List(Texte("Lien"))),Texte("voiture de luxe."))))))),"lien"))
  }

}
