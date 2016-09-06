package nz.daved.elysium.core

import scala.collection.mutable.ArrayBuffer

case class OperatorTestObject(var numbers: ArrayBuffer[Int] = new ArrayBuffer()) {

  @Operator("+|")
  def prepend(i: Int) = numbers.prepend(i)

  @Operator("|+")
  def append(i: Int) = numbers.append(i)

  @Operator("++")
  def combine(is: Seq[Int]) = numbers ++= is

}
