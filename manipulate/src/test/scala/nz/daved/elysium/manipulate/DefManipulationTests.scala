package nz.daved.elysium.manipulate

import org.scalatest.{FlatSpec, Matchers}

import scala.meta._
import DefManipulation.DefImplicits

import scala.meta.Defn.Def


class DefManipulationTests extends FlatSpec with Matchers {

  "prepend" should "prepend with single expr" in {
    val defn = convertToDef("def foo = 1").prependStat("2")
    defn match {
      case Defn.Def(_, _, _, _, _, Term.Block(Lit(2) :: Lit(1) :: Nil)) => assert(true)
      case _ => fail("prepend did not prepend")
    }
  }

  "prepend" should "prepend with multiple exprs" in {
    val defn = convertToDef("def foo = {1 ; 2}").prependStat("3")
    defn match {
      case Defn.Def(_, _, _, _, _, Term.Block(Lit(3) :: Lit(1) :: Lit(2) :: Nil)) => assert(true)
      case _ => fail("prepend did not prepend")
    }
  }

  "append" should "append with single expr" in {
    val defn = convertToDef("def foo = 1").appendStat("2")
    defn match {
      case Defn.Def(_, _, _, _, _, Term.Block(Lit(1) :: Lit(2) :: Nil)) => assert(true)
      case _ => fail("append did not append")
    }
  }

  "append" should "append with multiple exprs" in {
    val defn = convertToDef("def foo = {1 ; 2}").appendStat("3")
    defn match {
      case Defn.Def(_, _, _, _, _, Term.Block(Lit(1) :: Lit(2) :: Lit(3) :: Nil)) => assert(true)
      case _ => fail("append did not append")
    }
  }

  "replace" should "replace" in {
    val defn = convertToDef("def foo = 1").replaceStats(convertToDef("def hi = 5") :: Nil)
    defn match {
      case Defn.Def(_, _, _, _, _, Term.Block(foo :: Nil)) => assert(foo.isInstanceOf[Defn.Def])
      case _ => fail("replace did not replace")
    }
  }

  "delete" should "delete" in {
    val defn = convertToDef("def foo = 1").deleteStats
    defn match {
      case Defn.Def(_, _, _, _, _, Term.Block(Nil)) => assert(true)
      case _ => fail("delete did not delete")
    }
  }

  def convertToDef(s: String): Def =
    s.parse[Stat].get.asInstanceOf[Def]
}
