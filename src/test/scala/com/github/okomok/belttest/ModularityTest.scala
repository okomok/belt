

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok.belttest


import com.github.okomok.belt._
import Nat._


class ModularityTest extends org.scalatest.junit.JUnit3Suite {

     def not(b: Boolean) = b.not.self
     def notNot(b: Boolean) = not(not(b)).self
     def notNotNot(b: Boolean) = not(not(not(b))).self

     def inc(n: Nat) = n.increment.self
     def incInc(n: Nat) = inc(inc(n)).self

    def testTrivial {
        val b1: `true`.type = not(`false`)
        val b2: `false`.type = notNot(`false`)
        val b3: `true`.type = notNotNot(`false`)
    }

    object X extends Any
    object Y extends Any
    object Z extends Any

/*
    def testList {
        val xs = X :: Y :: Nil
        val x: X.type = xs.head
        val y: Y.type = xs.tail.head
    }
*/
    def testListCons {
        val xs = Cons(X, Cons(Y, Cons(Z, Nil)))
        val x: X.type = xs.head
        val y: Y.type = xs.tail.head
        val z: Z.type = xs.tail.tail.head
        val n: Nil.type = xs.tail.tail.tail
    }
}
