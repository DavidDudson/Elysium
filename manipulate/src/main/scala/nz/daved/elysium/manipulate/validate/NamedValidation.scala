package nz.daved.elysium.manipulate.validate

import nz.daved.elysium.manipulate.traits.Named
import nz.daved.elysium.manipulate.traits.Named._

object NamedValidation {
  implicit object NamedValidatable extends Validatable[Named[_]]
}
