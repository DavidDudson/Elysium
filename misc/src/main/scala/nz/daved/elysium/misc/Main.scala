package nz.daved.elysium.misc

import nz.daved.elysium.gen.CompileTime
import scala.meta._

@CompileTime
class Main extends scala.annotation.StaticAnnotation {
  inline def apply(defn: Any): Any = meta {
    val q"object $name { ..$stats }" = defn
    val main = q"def main(args: Array[String]): Unit = { ..$stats }"
    q"object $name { $main }"
  }
}
