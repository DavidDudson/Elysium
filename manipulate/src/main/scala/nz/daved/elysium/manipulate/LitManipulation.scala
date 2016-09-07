package nz.daved.elysium.manipulate

import nz.daved.elysium.manipulate.traits.HasName

import scala.meta._

trait LitManipulation {
  implicit class LitImplicits(lit: Lit) extends HasName {
    override val name: String = lit match {
      case Lit(s: String)  => s
      case other => sys.error(s"Cannot retrieve name from: ${other.show[Syntax]} as it is not supported yet")
    }
  }

}

object LitManipulation extends LitManipulation
