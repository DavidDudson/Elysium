package nz.daved.elysium.manipulate

import scala.collection.immutable
import scala.meta._

trait DefManipulation {
  implicit class DefImplicits(defn: Defn.Def) {
    def appendStat(stat: String): Defn.Def = appendStat(stat.parse[Stat].get)
    def appendStat(stat: Stat): Defn.Def = {
      defn match {
        case Defn.Def(_,_,_,_,_, Term.Block(stats)) =>
          defn.copy(body = Term.Block(stats :+ stat))
        case Defn.Def(_,_,_,_,_, singleStat) =>
          defn.copy(body = Term.Block(singleStat :: stat :: Nil))
      }
    }

    def prependStat(stat: String): Defn.Def = prependStat(stat.parse[Stat].get)
    def prependStat(stat: Stat): Defn.Def = {
      defn match {
        case Defn.Def(_,_,_,_,_, Term.Block(stats)) =>
          defn.copy(body = Term.Block(stat +: stats))
        case Defn.Def(_,_,_,_,_, singleStat) =>
          defn.copy(body = Term.Block(stat :: singleStat :: Nil))
      }
    }

    def replaceStats(stats: immutable.Seq[Stat]): Defn.Def =
      defn.copy(body = Term.Block(stats))

    def deleteStats: Defn.Def = defn.copy(body = Term.Block(Nil))


    // TODO: Make HasName a typeclass and use that instead
    def rename(name: Term.Name): Defn.Def =
      defn.copy(name = name)
  }
}

object DefManipulation extends DefManipulation
