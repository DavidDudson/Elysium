package nz.daved.elysium.test

import scala.meta._

@storeState("initial")
case class StoreTestObject() {
  def baz = 2
}
