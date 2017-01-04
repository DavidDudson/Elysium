package nz.daved.elysium.core

import org.scalatest.{FlatSpec, Matchers}

class DecoratorCompileTest extends FlatSpec with Matchers {
  "@before" should "compile with valid params" in {
    "@before(() => 2) def foo = {}" should compile
  }
}
