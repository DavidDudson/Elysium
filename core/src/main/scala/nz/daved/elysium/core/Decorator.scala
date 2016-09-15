package nz.daved.elysium.core

import nz.daved.elysium.gen.CompileTime
import scala.annotation.StaticAnnotation
import nz.daved.elysium.manipulate.DefManipulation._
import scala.meta.Defn.Def
import scala.meta._

@CompileTime
class Before(fun: () => Unit) extends StaticAnnotation {
  inline def apply(defn: Any): Any = meta {
    val q"new $_(() => ${apply: Term.Apply})" = this
    defn.asInstanceOf[Def].prependStat(apply)
  }
}

@CompileTime
class After(fun: () => Unit) extends StaticAnnotation {
  inline def apply(defn: Any): Any = meta {
    val q"new $_(() => ${apply: Term.Apply})" = this
    defn.asInstanceOf[Def].appendStat(apply)
  }
}
