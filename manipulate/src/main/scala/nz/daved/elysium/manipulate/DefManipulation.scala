package nz.daved.elysium.manipulate

import scala.collection.immutable
import scala.meta._

trait DefManipulation {
  implicit class DefImplicits(defn: Defn.Def) {
    def appendStat(stat: String): Defn.Def = appendStat(stat.parse[Stat].get)
    def appendStat(stat: Stat): Defn.Def = {
      defn match {
        case q"..$mods def $name[..$tparams](...$paramss): $tpeopt = { ..${stats: immutable.Seq[Stat]} }" =>
          q"..$mods def $name[..$tparams](...$paramss): $tpeopt = { ..$stats; $stat}".asInstanceOf[Defn.Def]
        case q"..$mods def $name[..$tparams](...$paramss): $tpeopt = $expr" =>
          q"..$mods def $name[..$tparams](...$paramss): $tpeopt = { $expr; $stat }".asInstanceOf[Defn.Def]
      }
    }

    def prependStat(stat: String): Defn.Def = prependStat(stat.parse[Stat].get)
    def prependStat(stat: Stat): Defn.Def = {
      defn match {
        case q"..$mods def $name[..$tparams](...$paramss): $tpeopt = { ..${stats: immutable.Seq[Stat]} }" =>
          q"..$mods def $name[..$tparams](...$paramss): $tpeopt = { $stat ; ..$stats}".asInstanceOf[Defn.Def]
        case q"..$mods def $name[..$tparams](...$paramss): $tpeopt = $expr" =>
          q"..$mods def $name[..$tparams](...$paramss): $tpeopt = { $stat ; $expr }".asInstanceOf[Defn.Def]
      }
    }

    def replaceStats(stats: immutable.Seq[Stat]): Defn.Def = {
      defn match {
        case q"..$mods def $name[..$tparams](...$paramss): $tpeopt = $_" =>
          q"..$mods def $name[..$tparams](...$paramss): $tpeopt = { ..$stats }".asInstanceOf[Defn.Def]
      }
    }

    def deleteStats: Defn.Def = replaceStats(immutable.Seq())

    // TODO: Make HasName a typeclass and use that instead
    def rename(name: Term.Name): Defn.Def = {
      val result: Tree = defn match {
        case q"..$mods def $_[..$tparam](...$params):$treturn = { ..$stats }" =>
          q"..$mods def $name[..$tparam](...$params):$treturn = { ..$stats }"
        case q"..$mods def $_[..$tparam](...$params):$treturn = $expr" =>
          q"..$mods def $name[..$tparam](...$params):$treturn = $expr"
      }

      //TODO: Replace with some same casting method that gives decent error messages
      result.asInstanceOf[Defn.Def]
    }
  }
}

object DefManipulation extends DefManipulation
