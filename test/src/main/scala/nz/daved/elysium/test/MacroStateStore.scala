package nz.daved.elysium.test

import scala.annotation.StaticAnnotation
import scala.meta._

import nz.daved.elysium.manipulate.LitManipulation._
import nz.daved.elysium.manipulate.ClassManipulation._

/** Create fields which statically contain information about the macro expansions */
class storeState(stateName: String) extends StaticAnnotation {
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
    val stat = Defn.Val(Nil, stateName :: Nil, None, clazz.structure.parse[Term].get)
    clazz.prependStat(stat)
  }
}
