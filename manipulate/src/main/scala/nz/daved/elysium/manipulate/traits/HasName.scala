package nz.daved.elysium.manipulate.traits

import scala.meta._

trait HasName {
  val name: String
  def asTermName: Term.Name = Term.Name(name)
  def asCtorRefName: Ctor.Name = Ctor.Name(name)
  def asTypeName: Type.Name = Type.Name(name)
}
