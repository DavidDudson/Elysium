package nz.daved.elysium.core

import org.scalatest.{FlatSpec, Matchers, BeforeAndAfter}


class OperatorTests extends FlatSpec with Matchers with BeforeAndAfter {

  var testObject = OperatorTestObject()

  before {
     testObject.numbers.clear
  }

  "Prepend operator" should "be interchangeable" in {
    testObject +| 1
    testObject.prepend(2)

    testObject.numbers shouldEqual Seq(2, 1)
  }

  "Combine operator" should "be interchangeable" in {
    testObject ++ Seq(3, 4)
    testObject.combine(Seq(5, 6))

    testObject.numbers shouldEqual Seq(3, 4, 5, 6)
  }

  "Append alternate" should "be interchangeable" in {
    testObject.append(1)
    testObject.add(3)

    testObject.numbers shouldEqual Seq(1, 3)
  }
}