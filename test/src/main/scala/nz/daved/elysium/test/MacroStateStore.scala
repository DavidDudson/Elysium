package nz.daved.elysium.test

import nz.daved.elysium.gen.macroAnnotation

import scala.meta._
import nz.daved.elysium.manipulate.Implicits._

object MacroStateStore {

  /**
    * Stores the current scala.meta tree representation as a string in the class body
    *
    * Todo: Ignore existing state val's to enable chaining of these calls
    */
  @macroAnnotation
  def storeStateString(clazz: Defn.Class)(stateName: String): Defn.Class = {
    if (stateName.isEmpty) {
      abortT(clazz, s"Duplicate method name must be non-empty")
    }

    if (MacroStateStore.containsWhitespace(stateName)) {
      abortT(clazz, s"'$stateName' contains whitespace and cannot be used as a method name")
    }

    val stateNameTerm: Pat.Var.Term = Pat.Var.Term(Term.Name(stateName ++ "State"))
    val stat = Defn.Val(Nil, stateNameTerm :: Nil, None, Lit(clazz.structure))

    clazz.prependStats(q"import scala.meta._".asInstanceOf[Stat], stat)
  }

  /**
    * Stores the current scala.meta tree representation as a meta.tree in the class body
    *
    * Todo: Ignore existing state val's to enable chaining of these calls
    */
  @macroAnnotation
  def storeState(clazz: Defn.Class)(stateName: String): Defn.Class = {
    if (stateName.isEmpty) {
      abortT(clazz, s"Duplicate method name must be non-empty")
    }

    if (MacroStateStore.containsWhitespace(stateName)) {
      abortT(clazz, s"'$stateName' contains whitespace and cannot be used as a method name")
    }

    val stateNameTerm: Pat.Var.Term = Pat.Var.Term(Term.Name(stateName ++ "State"))
    val stat = Defn.Val(Nil, stateNameTerm :: Nil, None, q"${Lit(clazz.structure)}.parse[Term].get".asInstanceOf[Term])

    clazz.prependStats(q"import scala.meta._".asInstanceOf[Stat], stat)
  }

  def containsWhitespace(name: String): Boolean = "\\s".r.findFirstIn(name).isDefined
}
