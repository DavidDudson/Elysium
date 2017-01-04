package nz.daved.elysium.manipulate

import scala.meta._
import TermNameManipulation._

trait TermSelectManipulation {
  implicit class TermSelectImplicits(select: Term.Select) {
    def asTypeSelect(): Type.Select = {
      select.qual match {
        case name: Term.Name =>
          Type.Select(name, select.name.asTypeName)
        case select: Term.Select  =>
          Type.Select(select, select.name.asTypeName)
        case _ =>
          abort("Unsupported type select switcher")
      }
    }
  }
}

object TermSelectManipulation extends TermSelectManipulation
