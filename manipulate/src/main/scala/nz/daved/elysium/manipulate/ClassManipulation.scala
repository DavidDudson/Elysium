package nz.daved.elysium.manipulate

import scala.meta._

trait ClassManipulation {

  /** Class methods default to the MAIN body of the class */
  implicit class ClassImplicits(clazz: Defn.Class) {

    def appendStat(stat: String): Defn.Class = appendStat(stat.parse[Stat].get)

    def appendStat(stat: Stat): Defn.Class = {
      clazz match {
        case Defn.Class(_, _, _, _, t@Template(_, _, _, stats)) =>
          clazz.copy(templ = t.copy(stats.getOrElse(Nil) :+ stat))
        case _ =>
          throw new IllegalStateException(s"appendStat does not support $clazz")
      }
    }

    def prependStat(stat: String): Defn.Class = prependStats(stat.parse[Stat].get)

    def prependStats(stats: Stat*): Defn.Class = prependStats(stats.toList)

    def prependStats(newStats: List[Stat]): Defn.Class = {
      clazz match {
        case Defn.Class(_, _, _, _, t@Template(_, _, _, stats)) =>
          clazz.copy(templ = t.copy(stats = Some(newStats ++ stats.getOrElse(Nil))))
        case _ =>
          throw new IllegalStateException(s"prependStat does not support $clazz")
      }
    }
  }
}

object ClassManipulation extends ClassManipulation