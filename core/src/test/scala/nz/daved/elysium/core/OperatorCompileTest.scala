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

  "negate" should "compile with valid params" in {
    "@negate(\"bar\") def foo = true" should compile
    "@negate(\"HJSDKHdsae7ewq8ewq\") def foo = true" should compile
    "@negate(\"Hªdsa\") def foo = true" should compile
  }

  it should "not compile with invalid params" in {
    "@negate(\"\") def foo = true" shouldNot compile
    "@negate(\"  \") def foo = true" shouldNot compile
    "@negate(\"dsajkdsa  dsajdsa\") def foo = true" shouldNot compile
    "@negate(\"  d\") def foo = true" shouldNot compile
    "@negate(\"d \") def foo = true" shouldNot compile
  }

//  Todo: Enable
//  it should "not compile with non-boolean return" in {
//    "@negate(\"\") def foo = 2" shouldNot compile
//  }
}


