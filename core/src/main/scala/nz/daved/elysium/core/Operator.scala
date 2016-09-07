package nz.daved.elysium.core

import nz.daved.elysium.manipulate.TermNameManipulation._
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
class Operator(name: String) extends StaticAnnotation {
  inline def apply(defn: Any): Any = meta {
    val q"new $_($arg)" = this
    val argName: Term.Name = arg match {
      case Term.Arg.Named(termName, _) => termName
      case Lit(literal) => Term.Name(literal.toString)
      case c => abort("other: " + c.show[Structure])
    }

    if (argName.containsWhitespace) {
      abort(s"'${argName.value}' contains whitespace and cannot be used as an operator")
    }

    if (argName.isSymbolic) {
      abort(s"'${argName.value}' is not symbolic and cannot be used as an operator")
    }

    val extraMethod: Tree = defn match {
      case q"..$mods def $_[..$tparam](...$params):$treturn = { ..$stats }" =>
        q"..$mods def $argName[..$tparam](...$params):$treturn  = { ..$stats }"
      case q"..$mods def $_[..$tparam](...$params):$treturn = $expr" =>
        q"..$mods def $argName[..$tparam](...$params):$treturn  = $expr"
    }

    q"$defn; ${extraMethod.asInstanceOf[Stat]}"
  }
}
