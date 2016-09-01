package nz.daved.inline_macros.printers

import scala.annotation.StaticAnnotation
import scala.meta._
import pprint._

class PrintStructure extends StaticAnnotation {
  inline def apply(defn: Defn) = meta {
    pprintln(defn.show[Structure])
    defn
  }
}

class PrintSyntax extends StaticAnnotation {
  inline def apply(defn: Defn) = meta {
    pprintln(defn.show[Syntax])
    defn
  }
}

