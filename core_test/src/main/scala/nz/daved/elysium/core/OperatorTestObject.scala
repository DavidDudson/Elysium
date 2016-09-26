package nz.daved.elysium.core

import scala.collection.mutable.ArrayBuffer

case class OperatorTestObject(var numbers: ArrayBuffer[Int] = new ArrayBuffer()) {

  @Operator("+|")
  def prepend(i: Int): Unit = numbers.prepend(i)

  @Alias("add")
  def append(i: Int): Unit = numbers.append(i)

  @Operator("++")
  def combine(is: Seq[Int]): Unit = numbers ++= is

}
