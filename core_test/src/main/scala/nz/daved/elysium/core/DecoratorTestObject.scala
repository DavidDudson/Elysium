package nz.daved.elysium.core

object DecoratorTestObject {

  @Before(() => print("hello"))
  def world(): Unit = println(" world")

  @After(() => println(" world"))
  def hello(): Unit = print("hello")
}
