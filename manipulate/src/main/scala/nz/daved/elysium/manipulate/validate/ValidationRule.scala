package nz.daved.elysium.manipulate.validate

import cats.implicits._
import cats.data.Validated

import StringValidation._
import CharValidation._

object Validatable {

  // TODO: Replace with @AltName and @Negated
  implicit class ValidatableOps[A: ValidationRule](a: A) {

    def is[F[_] <: Is[A]]: Boolean = implicitly[F[A]].validate(a)
    def has[F[_] <: Has[A]]: Boolean = implicitly[F[A]].validate(a)
    def isNot[F[_] <: Is[A]]: Boolean = !is[F]
    def hasNot[F[_] <: Has[A]]: Boolean = !has[F]

    def validateIs[F[_] <: Is[A]]: Validated[String, A] =
      if (a.is[F]) a.valid else implicitly[F[A]].positiveMessage(a).invalid

    def validateIsNot[F[_] <: Is[A]]: Validated[String, A] =
      if (a.isNot[F]) a.valid else implicitly[F[A]].negativeMessage(a).invalid

    def validateHas[F[_] <: Has[A]]: Validated[String, A] =
      if (a.has[F]) a.valid else implicitly[F[A]].positiveMessage(a).invalid

    def validateHasNo[F[_] <: Has[A]]: Validated[String, A] =
      if (a.hasNot[F]) a.valid else implicitly[F[A]].negativeMessage(a).invalid
  }
}

trait ValidationRule[A] extends ValidationContext[A] {
  def validate(a: A): Boolean
}

trait ValidationContext[A] {
  def positiveMessage(a: A): String
  def negativeMessage(a: A): String
}

trait Has[A] extends ValidationRule[A] {
  val message: String
  override def positiveMessage(a: A): String = s"$a must have $message"
  override def negativeMessage(a: A): String = s"$a must not have $message"
}

trait Is[A] extends ValidationRule[A] {
  val message: String
  override def positiveMessage(a: A): String = s"$a must be $message"
  override def negativeMessage(a: A): String = s"$a must not be $message"
}