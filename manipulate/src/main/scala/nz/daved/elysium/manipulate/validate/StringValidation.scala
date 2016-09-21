package nz.daved.elysium.manipulate.validate
import Validatable._
import CharValidation._

object StringValidation {
  implicit val symbolicString: Symbolic[String] = new Symbolic[String] {
    override def validate(a: String): Boolean = a.exists(c => c.is[Symbolic])
  }

  implicit val alphanumericString: Alphanumeric[String] = new Alphanumeric[String] {
    def validate(s: String): Boolean = s.exists(c => c.is[Alphanumeric])
  }

  implicit val whitespaceString: Whitespace[String] = new Whitespace[String] {
    def validate(s: String): Boolean = s.exists(c => c.has[Whitespace])
  }
}