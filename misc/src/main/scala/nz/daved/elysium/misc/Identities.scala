package nz.daved.elysium.misc

import scala.annotation.StaticAnnotation
import scala.meta._


class Identity extends StaticAnnotation {
  inline def apply(defn: Any): Any = meta(defn)
}

class CopyDef extends StaticAnnotation {
  implicit inline def apply(defn: Any): Any = meta {
    val q"..$mods def $name[..$tparam](...$params):$treturn = $expr" = defn
    q"..$mods def $name[..$tparam](...$params):$treturn  = $expr"
  }
}

