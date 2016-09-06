package nz.daved.elysium.debug

import org.scalatest.{FlatSpec, Matchers}

class PrinterTest extends FlatSpec with Matchers {

  "@PrintStructure" should "compile" in {
    "@PrintStructure def foo() = {}" should compile
  }

  "@PrintSyntax" should "compile" in {
    "@PrintSyntax def foo() = {}" should compile
  }

}
