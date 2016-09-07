package nz.daved.elysium.core

import nz.daved.elysium.manipulate.LitManipulation._
import nz.daved.elysium.manipulate.DefManipulation._

import scala.annotation.{StaticAnnotation, compileTimeOnly}
import scala.meta._

/**
  * Generates an identical method with a different name. designed for Symbolic operators.
  * The current implementation just generates a duplicate method. however this may be undesired.
  *
  * This symbolic operator MUST be 1 -> 5 characters long and only contain no alphanumerics.
  * If you disagree with this file a ticket with reasoning.
  *
  * With this in mind, You want this macro to expand last, so this annotation must be Bottom/Right.
  *
  * eg.
  * def append(i: Int)
  * def +(i: Int)
  */
@compileTimeOnly("@Operator not expanded")
class Operator(nameArg: String) extends StaticAnnotation {
  inline def apply(defn: Any): Any = meta {
    val q"new $_(${arg: Lit})" = this

    if (arg.containsWhitespace) {
      abort(s"'${arg.name}' contains whitespace and cannot be used as an operator")
    }

    if (!arg.isSymbolic) {
      abort(s"'${arg.name}' is not symbolic and cannot be used as an operator")
    }

    val newMethod: Defn.Def = defn.asInstanceOf[Defn.Def].rename(arg.asTermName)

    q"$defn; $newMethod"
  }
}
