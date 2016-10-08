package nz.daved.elysium.misc

import scala.annotation.{StaticAnnotation, compileTimeOnly}
import scala.meta._


@compileTimeOnly("@identity not expanded")
class identity extends StaticAnnotation {
  inline def apply(defn: Any): Any = meta(defn)
}

@compileTimeOnly("@copyDef not expanded")
class copyDef extends StaticAnnotation {
  inline def apply(a: Any): Any = meta {
    a match {
      case defn: Defn.Def =>
        defn.copy()
      case _ =>
        abort("@copyDef only supports Defn")
    }
  }
}

