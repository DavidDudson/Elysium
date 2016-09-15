package nz.daved.elysium.manipulate

import nz.daved.elysium.manipulate.traits.HasName
import scala.meta.Type

trait TypeNameManipulation {
  implicit class TypeNameImplicits(tpe: Type.Name) extends HasName {
    val name: String = tpe.value.toString
  }

}

object TypeNameManipulation extends TypeNameManipulation
