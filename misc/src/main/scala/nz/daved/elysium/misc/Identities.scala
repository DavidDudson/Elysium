package nz.daved.elysium.misc

import nz.daved.elysium.gen.macroAnnotation

import scala.meta._

object Indentities {
  @macroAnnotation
  def identity(stat: Stat): Stat = stat

  @macroAnnotation
  def copyDef(deff: Defn.Def): Defn.Def = deff.copy()
}

