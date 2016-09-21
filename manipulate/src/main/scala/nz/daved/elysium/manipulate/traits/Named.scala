package nz.daved.elysium.manipulate.traits

import scala.meta._

trait Named[A] {
  def name(a: A): String
  def from[B: Named](b: B): A
}

object Named {

  def apply[A: Named]: Named[A] = implicitly[Named[A]]

  implicit class NamedOps[A: Named](a: A) {
    val name = Named[A].name(a)
    def as[B: Named]: B = Named[B].from[A](a)
  }

  implicit object NamedTermName extends Named[Term.Name] {
    def name(t: Term.Name) = t.value
    override def from[B: Named](b: B): Term.Name = Term.Name(b.name)
  }

  implicit object NamedCtorRef extends Named[Ctor.Name] {
    def name(t: Ctor.Name) = t.value
    override def from[B: Named](b: B): Ctor.Name = Ctor.Name(b.name)
  }

  implicit object NamedTypeName extends Named[Type.Name] {
    def name(t: Type.Name) = t.value
    override def from[B: Named](b: B): Type.Name = Type.Name(b.name)
  }
}

