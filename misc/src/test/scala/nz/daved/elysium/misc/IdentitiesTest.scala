package nz.daved.elysium.misc

import org.scalatest.{FlatSpec, Matchers}

class IdentitiesTest extends FlatSpec with Matchers {

  "@identity" should "compile" in {
    "@identity def main() = {}" should compile
    "@identity val hi = \"\"" should compile
    "@identity var hello = \"\"" should compile
    "@identity object SomeObject" should compile
    "@identity class SomeClass(a: String)" should compile
    "@identity case class SomeCaseClass(a: String)" should compile
    "@identity trait SomeTrait" should compile
    "@identity sealed trait SomeSealedTrait" should compile
  }

  "@copy" should "compile" in {
    "@copyDef def main() = \"\"" should compile
  }
}
