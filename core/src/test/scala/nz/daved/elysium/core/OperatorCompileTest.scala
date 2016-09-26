package nz.daved.elysium.core

import org.scalatest.{FlatSpec, Matchers}

class OperatorCompileTest extends FlatSpec with Matchers {

  "Operator" should "compile with valid params" in {
    "@Operator(\"+\") def foo = {}" should compile
    "@Operator(\"+=\") def foo = {}" should compile
    "@Operator(\"+++++\") def foo = {}" should compile
  }

  it should "not compile with invalid params" in {
    "@Operator(\"abc\") def foo = {}" shouldNot compile
    "@Operator(\"123\") def foo = {}" shouldNot compile
    "@Operator(\"(dsha\") def foo = {}" shouldNot compile
    "@Operator(\"=> \") def foo = {}" shouldNot compile
    "@Operator(\"$\t\") def foo = {}" shouldNot compile
    "@Operator(\"=\n\") def foo = {}" shouldNot compile
    "@Operator(\" \") def foo = {}" shouldNot compile
    "@Operator(\"\") def foo = {}" shouldNot compile
    "@Operator(\"::::::\") def foo = {}" shouldNot compile
    "@Operator() def foo = {}" shouldNot compile
    "@Operator def foo = {}" shouldNot compile
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
