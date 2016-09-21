package nz.daved.elysium.manipulate

import nz.daved.elysium.manipulate.traits.Named

import scala.meta._

trait TermArgManipulation {
  implicit class TermArgImplicits(termArg: Term.Arg) extends Named {
    override val name: String = termArg match {
      case Term.Arg.Named(argName, _) => argName.name
      case term => sys.error(s"Cannot retrieve name from: ${term.show[Syntax]} as it is not supported yet")
    }
  }
}

object TermArgManipulation extends TermArgManipulation