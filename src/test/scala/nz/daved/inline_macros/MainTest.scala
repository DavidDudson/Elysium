package nz.daved.inline_macros

import java.io.{ByteArrayOutputStream, PrintStream}

import Test
import org.scalatest.{FlatSpec, Matchers}

class MainTest extends FlatSpec with Matchers {

  "Object annotated with @Main" should "have a main method that prints \"hello world\"" in {
    val out: ByteArrayOutputStream = new ByteArrayOutputStream()
    Console.withOut(new PrintStream(out)) {
      Test.main(Array())
    }
    out.toString shouldBe "hello world\n"
  }
}
