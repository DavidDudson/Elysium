package nz.daved.elysium.misc

import scala.annotation.{StaticAnnotation, compileTimeOnly}
import scala.meta._

@compileTimeOnly("@main not expanded")
class main extends StaticAnnotation {
  inline def apply(defn: Any): Any = meta {
    defn match {
      case q"object $name { ..$stats }" =>
        val main = q"def main(args: Array[String]): Unit = { ..$stats }"
        q"object $name { $main }"
      case _=>
        abort("@main does not support this tree")
    }
  }
}
