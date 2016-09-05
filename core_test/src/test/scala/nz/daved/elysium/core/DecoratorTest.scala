package nz.daved.elysium.core

import java.io.{ByteArrayOutputStream, PrintStream}

import org.scalatest.{FlatSpec, Matchers}

class DecoratorTest extends FlatSpec with Matchers {

  "@Before" should "deal with anonymous functions passed in" in {
    val out: ByteArrayOutputStream = new ByteArrayOutputStream()
    Console.withOut(new PrintStream(out)) {
      DecoratorTestObject.world()
    }
    out.toString.stripLineEnd shouldBe "hello world"
  }
}
