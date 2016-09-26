package nz.daved.elysium.misc

import scala.annotation.StaticAnnotation
import scala.meta._


class identity extends StaticAnnotation {
  inline def apply(defn: Any): Any = meta(defn)
}

class copyDef extends StaticAnnotation {
  implicit inline def apply(defn: Any): Any = meta {
    val q"..$mods def $name[..$tparam](...$params):$treturn = $expr" = defn
    q"..$mods def $name[..$tparam](...$params):$treturn  = $expr"
  }
}

