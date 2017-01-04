package nz.daved.elysium.debug

import org.scalatest.{FlatSpec, Matchers}

import Printers._

class PrinterTest extends FlatSpec with Matchers {

  "@printStructure" should "compile" in {
    "@printStructure def foo() = {}" should compile
  }

  "@printSyntax" should "compile" in {
    "@printSyntax def foo() = {}" should compile
  }

}
