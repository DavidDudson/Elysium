package nz.daved

import scala.annotation.compileTimeOnly
import scala.meta._

@compileTimeOnly("@nz.daved.Main not expanded")
class Main extends scala.annotation.StaticAnnotation {
  inline def apply(defn: Defn) = meta {
    val q"object $name { ..$stats }" = defn
    val main = q"""
      def main(args: Array[String]): Unit = { ..$stats }
    """

    q"object $name { $main }"
  }
}
