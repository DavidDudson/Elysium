package nz.daved.elysium.misc

import nz.daved.elysium.gen.macroAnnotation

import scala.meta._

object Main {
  @macroAnnotation
  def main(obj: Defn.Object): Defn.Object = {
    // TODO: Uncomment once https://github.com/scalameta/scalameta/pull/588 is merged
    obj
//    obj match {
//      case q"object $name { ..$stats }" =>
//        val main: Stat = q"def main(args: Array[String]): Unit = { ..$stats }"
//        q"object $name { $main }"
//      case _=>
//        abort("@main does not support this tree")
//    }
  }
}
