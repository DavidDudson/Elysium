package nz.daved.elysium.manipulate.validate

trait Symbolic[A] extends Is[A] {
  val message: String = "symbolic"
}

trait Alphanumeric[A] extends Is[A] {
  val message: String = "alphanumeric"
}

// Todo revisit, try and make this nicer
trait Whitespace[A] extends Has[A] {
  override val message: String = "whitespace"
}

trait WhitespaceIs[A] extends Is[A] {
  override val message: String = "whitespace"
}

trait Empty[A] extends Is[A] {
  override val message: String = "empty"
}

trait NonEmpty[A] extends Is[A] {
  override val message: String = "nonEmpty"
}

trait NonNull[A] extends Is[A] {
  override val message: String = "nonNull"
}

trait Null[A] extends Is[A] {
  override val message: String = "nul"
}

/** Probably only useful for numbers */
trait Natural[A] extends Is[A] {
  override val message: String = "natural"
}

