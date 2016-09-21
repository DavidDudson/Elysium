package nz.daved.elysium.manipulate

import nz.daved.elysium.manipulate.traits.Named

import scala.meta._

trait TermNameManipulation {
  implicit class TermNameImplicits(termName: Term.Name) extends Named {
    // TODO: Alter this so it takes extra things into consideration (like anonymous classes)
    // This is really just a temperary hack
    /** Defines the name of the term, this does not include package etc. */
    val name: String = termName.value.toString
  }

}

object TermNameManipulation extends TermNameManipulation
