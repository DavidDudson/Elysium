package nz.daved.elysium.manipulate.traits

import scala.meta._

trait HasName {
  val name: String
  def asTermName: Term.Name = Term.Name(name)
  def asCtorRefName: Ctor.Name = Ctor.Name(name)
  def asTypeName: Type.Name = Type.Name(name)

  /** Ensures 1 -> 5 characters, non word (no numbers or letters) */
  def isSymbolic: Boolean = "^\\W{1,5}$".r.findFirstIn(name).isDefined
  def containsWhitespace: Boolean = "\\s".r.findFirstIn(name).isDefined
}
