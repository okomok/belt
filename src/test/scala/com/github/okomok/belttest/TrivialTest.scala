

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok.belttest


import com.github.okomok.belt._


class TrivialTest extends org.scalatest.junit.JUnit3Suite {

    object X extends Singleton
    object Y extends Singleton

    object GetX extends Value {
        override type value = X.type
        override  val value: value = X
    }

    def testValue {
        def get(n: Value) = n.value
        val ret1: X.type = get(X)
        val ret2: X.type = get(GetX)
    }

    def not(b: Boolean)/*: b.not*/ = b.not
    def not2(b: Boolean)/*: b.not*/ = not(b)
    def notNot(b: Boolean)/*: b.not.not*/ = not(b.not) // not(not(b)) error
    def notNotNot(b: Boolean)/*: b.not.not.not*/ = b.not.not.not

    def testNot {
        val ret1: True.type = not(False)
        val ret2: False.type = not(True)
    }

    def testNot2 {
        val ret1: True.type = not2(False)
        val ret2: False.type = not2(True)
    }

    def testNotNot {
        val ret1: True.type = notNot(True)
        val ret2: False.type = notNot(False)
    }

    def testNotNotNot {
        val ret1: True.type = notNotNot(False)
        val ret2: False.type = notNotNot(True)
    }

    def testNat {
        val ret1: _1.type = _0.increment
        val ret2: _2.type = _3.decrement
    }

    def testNatOps {
        def incdecinc(n: Nat) = n.increment.decrement.increment
        val ret1: _5.type = incdecinc(_4)
    }

    def testList {
        val ys = Cons(Y, Nil) // named val needed for some reason.
        val xs = Cons(X, ys)
        val x: X.type = xs.head
        val y: Y.type = xs.tail.head
    }
}
