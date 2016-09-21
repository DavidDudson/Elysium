package nz.daved.elysium.manipulate

import scala.meta._

trait LitManipulation {
  implicit class LitImplicits(lit: Lit) {
    val value: String = lit match {
      case Lit(s: String)  => s
      case other => sys.error(s"Cannot retrieve name from: ${other.show[Syntax]} as it is not supported yet")
    }
  }
}

object LitManipulation extends LitManipulation
