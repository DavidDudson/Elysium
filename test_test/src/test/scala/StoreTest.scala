import nz.daved.elysium.test.{StoreStringTestObject, StoreTestObject}
import org.scalatest.{FlatSpec, Matchers}

import scala.meta._

class StoreTest extends FlatSpec with Matchers {
  private val stringBasedTestObject = StoreStringTestObject()
  private val testObject = StoreTestObject()

  "StringTestObject" should "have member initialState with string representation of structure" in {
    stringBasedTestObject.initialState match {
      case a: String => println(a)
      case _ => fail()
    }
  }

  "TestObject" should "have member initialState with tree" in {
    testObject.initialState match {
      case a: Term => println(a)
      case _ => fail()
    }
  }
}
