package nz.daved.elysium.core

object DecoratorTestObject {

  @before(print("hello"))
  def world(): Unit = println(" world")

  @after(println(" world"))
  def hello(): Unit = print("hello")
}
