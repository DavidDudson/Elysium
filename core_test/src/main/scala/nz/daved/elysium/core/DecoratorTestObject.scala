package nz.daved.elysium.core

object DecoratorTestObject {

  @Before(() => print("hello"))
  def world() = println(" world")
}
