package nz.daved.elysium.debug

import nz.daved.elysium.gen.macroAnnotation

import scala.meta._

object Printers {

  @macroAnnotation
  def printStructure(defn: Stat): Stat = {
    println(defn.show[Structure])
    defn
  }

  @macroAnnotation
  def printSyntax(defn: Stat): Stat = {
    println(defn.show[Syntax])
    defn
  }
}
