package nz.daved.elysium.debug

import pprint._

import scala.annotation.StaticAnnotation
import scala.meta._

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

