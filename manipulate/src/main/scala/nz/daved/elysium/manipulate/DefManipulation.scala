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
        case _ =>
          abort("append stat noes not support this class pattern")
      }
    }

    def prependStat(stat: String): Defn.Def = prependStat(stat.parse[Stat].get)
    def prependStat(stat: Stat): Defn.Def = {
      defn match {
        case Defn.Def(_,_,_,_,_, Term.Block(stats)) =>
          defn.copy(body = Term.Block(stat +: stats))
        case Defn.Def(_,_,_,_,_, singleStat) =>
          defn.copy(body = Term.Block(stat :: singleStat :: Nil))
        case _ =>
          abort("prepend stat noes not support this class pattern")
      }
    }

    def replaceStats(stats: immutable.Seq[Stat]): Defn.Def =
      defn.copy(body = Term.Block(stats))

    def deleteStats: Defn.Def = defn.copy(body = Term.Block(Nil))


    // TODO: Make HasName a typeclass and use that instead
    def rename(n: String): Defn.Def =
      rename(Term.Name(n))

    def rename(n: Term.Name): Defn.Def =
      defn.copy(name = n)
  }
}

object DefManipulation extends DefManipulation
