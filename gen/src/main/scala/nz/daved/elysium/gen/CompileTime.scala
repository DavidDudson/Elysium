package nz.daved.elysium.gen

import scala.meta._
import nz.daved.elysium.manipulate.TypeNameManipulation._
import nz.daved.elysium.manipulate.ClassManipulation._

/**
  * A better version of @compileTimeOnly that removes the duplication of "@X is not expanded"
  *
  * This is currently designed for macro classes only (This may e extended to defs aswell later)
  */
class CompileTime {
  inline def apply(annotee: Any): Any = meta {
    val clazz: Defn.Class = annotee.asInstanceOf[Defn.Class]
    val errorMessage: String = s"${clazz.name.name} is not expanded"
    clazz.appendMod(mod"@compileTimeOnly($errorMessage)")
  }
}
