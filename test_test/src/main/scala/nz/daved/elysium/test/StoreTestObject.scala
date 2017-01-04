package nz.daved.elysium.test

import scala.meta._
import nz.daved.elysium.test.MacroStateStore._

@storeStateString("initial")
case class StoreStringTestObject() {
  val foo = "test".parse[Term].get
  def baz = 2
}

@storeState("initial")
case class StoreTestObject() {
  def baz = 2
}