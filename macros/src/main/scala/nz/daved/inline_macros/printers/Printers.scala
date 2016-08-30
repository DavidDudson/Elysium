package nz.daved.inline_macros.printers

import scala.annotation.StaticAnnotation
import scala.meta._


class PrintStructure extends StaticAnnotation {
  inline def apply(defn: Defn) = meta {
    println(defn.show[Structure])
    defn
  }
}

class PrintSyntax extends StaticAnnotation {
  inline def apply(defn: Defn) = meta {
    println(defn.show[Syntax])
    defn
  }
}

