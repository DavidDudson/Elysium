package nz.daved.elysium.misc

import nz.daved.elysium.gen.CompileTime
import scala.annotation.StaticAnnotation
import scala.meta._


@CompileTime
class Identity extends StaticAnnotation {
  inline def apply(defn: Any): Any = meta(defn)
}

@CompileTime
class CopyDef extends StaticAnnotation {
  implicit inline def apply(defn: Any): Any = meta {
    val q"..$mods def $name[..$tparam](...$params):$treturn = $expr" = defn
    q"..$mods def $name[..$tparam](...$params):$treturn  = $expr"
  }
}

