package nz.daved.elysium.debug

import pprint._

import scala.annotation.{StaticAnnotation, compileTimeOnly}
import scala.meta._

@compileTimeOnly("@printStructure not expanded")
class printStructure extends StaticAnnotation {
  inline def apply(defn: Any): Any = meta {
    pprintln(defn.show[Structure])
    defn
  }
}

@compileTimeOnly("@printSyntax not expanded")
class printSyntax extends StaticAnnotation {
  inline def apply(defn: Any): Any = meta {
    pprintln(defn.show[Syntax])
    defn
  }
}

