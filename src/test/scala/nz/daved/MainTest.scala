package nz.daved

import java.io.{ByteArrayOutputStream, PrintStream}

import org.scalatest.{FunSuite, Matchers}

class MainTest extends FunSuite with Matchers {

  test("nz.daved.Main method should print hello world") {
    val out: ByteArrayOutputStream = new ByteArrayOutputStream()
    val ps: PrintStream = new PrintStream(out)
    Console.withOut(ps) {
      Test.main(Array())
    }
    out.toString shouldBe "hello world\n"
  }
}
