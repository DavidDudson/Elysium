package nz.daved.elysium.debug

import nz.daved.elysium.gen.CompileTime
import pprint._
import scala.annotation.StaticAnnotation
import scala.meta._

@CompileTime
class PrintStructure extends StaticAnnotation {
  inline def apply(defn: Any): Any = meta {
    pprintln(defn.show[Structure])
    defn
  }
}

@CompileTime
class PrintSyntax extends StaticAnnotation {
  inline def apply(defn: Any): Any = meta {
    pprintln(defn.show[Syntax])
    defn
  }
}

