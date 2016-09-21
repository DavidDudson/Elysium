package nz.daved.elysium.manipulate.validate

object CharValidation {
  implicit val symbolicString = new Symbolic[Char] {
    override def validate(c: Char): Boolean = !c.isLetterOrDigit
  }

  implicit val alphanumericString = new Alphanumeric[Char] {
    def validate(c: Char): Boolean = c.isLetterOrDigit
  }

  implicit val whitespaceString = new WhitespaceIs[Char] {
    def validate(c: Char): Boolean = c.isWhitespace
  }
}
