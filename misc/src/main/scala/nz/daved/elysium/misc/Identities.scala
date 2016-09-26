package nz.daved.elysium.misc

import scala.annotation.StaticAnnotation
import scala.meta._


class identity extends StaticAnnotation {
  inline def apply(defn: Any): Any = meta(defn)
}

class copyDef extends StaticAnnotation {
  implicit inline def apply(a: Any): Any = meta {
    a match {
      case defn: Defn.Def => defn.copy()
      case _ => abort("@CopyDef only supports Defn")
    }
  }
}

