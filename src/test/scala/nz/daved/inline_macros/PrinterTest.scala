package nz.daved.inline_macros

import nz.daved.inline_macros.printers.{PrintStructure, PrintSyntax}
import org.scalatest.{FlatSpec, Matchers}

class PrinterTest extends FlatSpec with Matchers {

  "@PrintStructure" should "compile" in {
    @PrintStructure
    def foo() = {}
  }

  "@PrintSyntax" should "compile" in {
    @PrintSyntax
    def foo() = {}
  }

}
