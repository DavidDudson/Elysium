package nz.daved.elysium.core

import scala.annotation.{StaticAnnotation, compileTimeOnly}
import nz.daved.elysium.manipulate.DefManipulation._

import scala.meta.Defn.Def
import scala.meta._

@compileTimeOnly("@Before not expanded")
class Before(fun: () => Unit) extends StaticAnnotation {
  inline def apply(defn: Any): Any = meta {
    val q"new $_(() => ${apply: Term.Apply})" = this
    defn.asInstanceOf[Def].prependStat(apply)
  }
}

@compileTimeOnly("@After not expanded")
class After(fun: () => Unit) extends StaticAnnotation {
  inline def apply(defn: Any): Any = meta {
    val q"new $_(() => ${apply: Term.Apply})" = this
    defn.asInstanceOf[Def].appendStat(apply)
  }
}