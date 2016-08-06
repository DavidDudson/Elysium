package nz.daved

import java.io.{ByteArrayOutputStream, PrintStream}

import org.scalatest.{FlatSpec, FunSuite, Matchers}

class MainTest extends FlatSpec with Matchers {

  "nz.daved.Main method" should "print hello world" in {
    val out: ByteArrayOutputStream = new ByteArrayOutputStream()
    val ps: PrintStream = new PrintStream(out)
    Console.withOut(ps) {
      Test.main(Array())
    }
    out.toString shouldBe "hello world\n"
  }
}
