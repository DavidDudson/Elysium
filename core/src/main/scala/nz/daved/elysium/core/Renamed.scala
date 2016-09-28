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
@compileTimeOnly("@operator not expanded")
class operator(nameArg: String) extends StaticAnnotation {
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


/**
  * Generates an identical method with a different name. designed for methods with multiple common names.
  * The current implementation just generates a duplicate method. however this may be undesired.
  *
  * eg.
  * def contains (from Java land)
  * def includes (From JS land)
  *
  */
@compileTimeOnly("@alias not expanded")
class alias(nameArg: String) extends StaticAnnotation {
  inline def apply(defn: Any): Any = meta {
    val q"new $_(${arg: Lit})" = this

    if (arg.name.isEmpty) {
      abort(s"Duplicate method name must be non-empty")
    }

    if (arg.containsWhitespace) {
      abort(s"'${arg.name}' contains whitespace and cannot be used as a method name")
    }

    val newMethod: Defn.Def = defn.asInstanceOf[Defn.Def].rename(arg.asTermName)

    q"$defn; $newMethod"
  }
}

/**
  * Negates a boolean returning method, must give a name
  *
  * Todo: Validate that the method is actually a boolean resulting method
  */
@compileTimeOnly("@alias not expanded")
class negate(nameArg: String) extends StaticAnnotation {
  inline def apply(defn: Any): Any = meta {
    val q"new $_(${arg: Lit})" = this

    if (arg.name.isEmpty) {
      abort(s"Duplicate method name must be non-empty")
    }

    if (arg.containsWhitespace) {
      abort(s"'${arg.name}' contains whitespace and cannot be used as a method name")
    }

    val newMethod = defn match {
      case q"..$mods def $_[..$tparams](...$paramss): $tpeopt = $expr" =>
        q"..$mods def ${arg.asTermName}[..$tparams](...$paramss): $tpeopt = !$expr"
      case _ =>
        abort("@negate only supports single expressions currently")
    }

    q"$defn; $newMethod"
  }
}
