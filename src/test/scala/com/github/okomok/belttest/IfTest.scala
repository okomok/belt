

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok.belttest


import com.github.okomok.belt._
import Nat._


class IfTest extends org.scalatest.junit.JUnit3Suite {

    def mustBeTrue(x: `true`.type) = ()
    def mustBeFalse(x: `false`.type) = ()

    case object X extends Function0 {
        override type apply = this.type
        override  val apply: apply = this
    }

    case object Y extends Function0 {
        override type apply = this.type
        override  val apply: apply = this
    }

    def testTrivial {
        val x: X.type = `true`.`if`(X, Y).apply
        val y: Y.type = `false`.`if`(X, Y).apply
    }

    def testTrivial2 {
        def callIf(n: Nat, m: Nat, y: Function0): n.self#equal[m.self]#`if`[X.self, y.self]#apply = `if`(n equal m, X, y).apply.self.asInstanceOf[n.self#equal[m.self]#`if`[X.self, y.self]#apply] // (n equal m).`if`(X, y).apply

        val x: X.type = callIf(_3, _3, Y).self
        val y: Y.type = callIf(_3, _4, Y).self

        println(x, y)
    }
}
