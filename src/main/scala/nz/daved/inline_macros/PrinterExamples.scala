package nz.daved.inline_macros

import nz.daved.inline_macros.printers.PrintStructure

// Uncomment the @PrintStructure to get the structure outputted on compile
object PrinterExamples {

  @PrintStructure
  def a = ""

  @PrintStructure
  def b() = {}

  @PrintStructure
  private def c = 123

  @PrintStructure
  private[this] final def d(someInputs: Int) = Option(null)

  @PrintStructure
  def e = {
    "" match {
      case "Hello" => 0
      case a:String if a.isEmpty => 1
      case _ => 2
    }
  }
}
