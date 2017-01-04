package nz.daved.elysium.manipulate

import scala.meta.Tree
import scala.meta.internal.inline.AbortException

object Implicits
  extends ClassManipulation
  with DefManipulation
  with LitManipulation
  with TermArgManipulation
  with TermNameManipulation
  with TermSelectManipulation {

  def abortT(t: Tree, msg: String): Nothing = {
    val newMsg =
      s"""
       |[Message]
       |===========================================
       |$msg
       |
       |[Syntax]
       |===========================================
       |${t.syntax}
       |
       |[Structure]
       |===========================================
       |${t.structure}
     """.stripMargin
    throw new AbortException(newMsg)
  }
}
