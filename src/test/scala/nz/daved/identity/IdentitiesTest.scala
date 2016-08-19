package nz.daved.identity

import org.scalatest.{FlatSpec, Matchers}

class IdentitiesTest extends FlatSpec with Matchers {

  "@Identity" should "compile" in {
    @Identity
    def main() = {}

    @Identity
    val hi = ""

    @Identity
    var hello = ""

    @Identity
    object SomeObject {}

    @Identity
    class SomeClass(a: String) {}

    @Identity
    case class SomeCaseClass(a: String) {}

    @Identity
    trait SomeTrait

    @Identity
    sealed trait SomeSealedTrait
  }

  "@Copy" should "compile" in {
    @Copy
    def main() = {}
  }
}
