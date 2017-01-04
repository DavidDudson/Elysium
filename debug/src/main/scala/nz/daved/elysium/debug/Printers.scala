package nz.daved.elysium.debug

import nz.daved.elysium.gen.macroAnnotation
import pprint._

import scala.meta._

object Printers {

  @macroAnnotation
  def printStructure(defn: Stat): Stat = {
    pprintln(defn.show[Structure])
    defn
  }

  @macroAnnotation
  def printSyntax(defn: Stat): Stat = {
    pprintln(defn.show[Syntax])
    defn
  }
}




