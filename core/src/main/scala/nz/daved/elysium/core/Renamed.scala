package nz.daved.elysium.core

import nz.daved.elysium.gen.macroAnnotation
import nz.daved.elysium.manipulate.Implicits._

import scala.meta._

object Renamed {

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
  @macroAnnotation
  def operator(deff: Defn.Def)(name: String): Term.Block = {

    if (Renamed.containsWhitespace(name)) {
      abortT(deff, s"'$name' contains whitespace and cannot be used as an operator")
    }

    if (!Renamed.isSymbolic(name)) {
      abortT(deff, s"'$name' is not symbolic and cannot be used as an operator")
    }

    val newMethod: Defn.Def = deff.rename(Term.Name(name))
    q"$deff; $newMethod"
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
  @macroAnnotation
  def alias(deff: Defn.Def)(name: String): Term.Block = {
    if (name.isEmpty) {
      abortT(deff, s"Duplicate method name must be non-empty")
    }

    if (Renamed.containsWhitespace(name)) {
      abortT(deff, s"'$name' contains whitespace and cannot be used as a method name")
    }

    val newMethod: Defn.Def = deff.rename(Term.Name(name))
    q"$deff; $newMethod"
  }


  /**
    * Negates a boolean returning method, must give a name
    *
    * Todo: Validate that the method is actually a boolean resulting method
    */
  @macroAnnotation
  def negate(deff: Defn.Def)(name: String): Term.Block = {
    if (name.isEmpty) {
      abortT(deff, s"Duplicate method name must be non-empty")
    }

    if (Renamed.containsWhitespace(name)) {
      abortT(deff, s"'$name' contains whitespace and cannot be used as a method name")
    }

    val newBody = deff.body match {
      case _: Term.Block =>
        abortT(deff, "@negate only supports single expression defs currently")
      case _ =>
        q"!${deff.body}"
    }

    val newMethod: Defn.Def = deff.rename(Term.Name(name)).copy(body = newBody)
    q"$deff; $newMethod"
  }


  /** Ensures 1 -> 5 characters, non word (no numbers or letters) */
  def isSymbolic(name: String): Boolean = "^\\W{1,5}$".r.findFirstIn(name).isDefined
  def containsWhitespace(name: String): Boolean = "\\s".r.findFirstIn(name).isDefined
}
