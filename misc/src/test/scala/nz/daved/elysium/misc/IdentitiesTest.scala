package nz.daved.elysium.misc

import org.scalatest.{FlatSpec, Matchers}

class IdentitiesTest extends FlatSpec with Matchers {

  "@Identity" should "compile" in {
    "@Identity def main() = {}" should compile
    "@Identity val hi = \"\"" should compile
    "@Identity var hello = \"\"" should compile
    "@Identity object SomeObject" should compile
    "@Identity class SomeClass(a: String)" should compile
    "@Identity case class SomeCaseClass(a: String)" should compile
    "@Identity trait SomeTrait" should compile
    "@Identity sealed trait SomeSealedTrait" should compile
  }

  "@Copy" should "compile" in {
    "@CopyDef def main() = \"\"" should compile
  }
}
