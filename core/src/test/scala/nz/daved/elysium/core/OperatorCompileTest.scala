package nz.daved.elysium.core

import org.scalatest.{FlatSpec, Matchers}

class operatorCompileTest extends FlatSpec with Matchers {

  "@operator" should "compile with valid params" in {
    "@operator(\"+\") def foo = {}" should compile
    "@operator(\"+=\") def foo = {}" should compile
    "@operator(\"+++++\") def foo = {}" should compile
  }

  it should "not compile with invalid params" in {
    "@operator(\"abc\") def foo = {}" shouldNot compile
    "@operator(\"123\") def foo = {}" shouldNot compile
    "@operator(\"(dsha\") def foo = {}" shouldNot compile
    "@operator(\"=> \") def foo = {}" shouldNot compile
    "@operator(\"$\t\") def foo = {}" shouldNot compile
    "@operator(\"=\n\") def foo = {}" shouldNot compile
    "@operator(\" \") def foo = {}" shouldNot compile
    "@operator(\"\") def foo = {}" shouldNot compile
    "@operator(\"::::::\") def foo = {}" shouldNot compile
    "@operator() def foo = {}" shouldNot compile
    "@operator def foo = {}" shouldNot compile
  }

  "alias" should "compile with valid params" in {
    "@alias(\"bar\") def foo = {}" should compile
    "@alias(\"HJSDKHdsae7ewq8ewq\") def foo = {}" should compile
    "@alias(\"Hªdsa\") def foo = {}" should compile
  }

  it should "not compile with invalid params" in {
    "@alias(\"\") def foo = {}" shouldNot compile
    "@alias(\"  \") def foo = {}" shouldNot compile
    "@alias(\"dsajkdsa  dsajdsa\") def foo = {}" shouldNot compile
    "@alias(\"  d\") def foo = {}" shouldNot compile
    "@alias(\"d \") def foo = {}" shouldNot compile
  }
}
