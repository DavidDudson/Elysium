package nz.daved.elysium.gen

import scala.annotation.StaticAnnotation
import scala.meta._

/**
  * This is as good as we can do since X should compile doesnt work where inline is involved
  *
  * If this compiles... It works... Kind of
  */
class CompileTimeOnlyTest {

  @CompileTime
  class Identity extends StaticAnnotation {
    inline def apply(defn: Any): Any = meta { defn }
  }
}
