package nz.daved.elysium.manipulate

import scala.meta.{Defn, Mod}

trait ClassManipulation {
  implicit class ClassImplicits(clazz: Defn.Class) {
    def appendMod(mod: Mod) = clazz.copy(mods = clazz.mods :+ mod)
    def prependMod(mod: Mod) = clazz.copy(mods = mod +: clazz.mods)
  }
}

object ClassManipulation extends ClassManipulation
