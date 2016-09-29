package nz.daved.elysium.test

import scala.annotation.StaticAnnotation
import scala.meta._

import nz.daved.elysium.manipulate.LitManipulation._
import nz.daved.elysium.manipulate.ClassManipulation._

/**
  * Stores the current scala.meta tree representation as a tree in the class body
  *
  * Todo: Ignore existing state val's to enable chaining of these calls
  */
class storeStateString(stateName: String) extends StaticAnnotation {
  inline def apply(a: Any): Any = meta {
    val q"new $_(${arg: Lit})" = this

    if (arg.name.isEmpty) {
      abort(s"Duplicate method name must be non-empty")
    }

    if (arg.containsWhitespace) {
      abort(s"'${arg.name}' contains whitespace and cannot be used as a method name")
    }

    // Verify we are actually annotating a class
    val clazz: Defn.Class = a match {
      case c: Defn.Class => c
      case other => abort(s"@storeState does not support ${other.getClass.getSimpleName}")
    }

    val stateName: Pat.Var.Term = Pat.Var.Term(Term.Name(arg.name ++ "State"))
    val stat = Defn.Val(Nil, stateName :: Nil, None, Lit(clazz.structure))

    clazz.prependStats(q"import scala.meta._".asInstanceOf[Stat], stat)
  }
}
