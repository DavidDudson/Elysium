import nz.daved.elysium.test.StoreTestObject
import org.scalatest.{FlatSpec, Matchers}

class StoreTest extends FlatSpec with Matchers {
  private val testObject = StoreTestObject()

  "TestObject" should "have member initialState with contents" in {
    testObject.initialState match {
      case a: String => println(a)
      case _ => fail()
    }
  }
}
