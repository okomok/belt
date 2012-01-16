

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok.belttest


import com.github.okomok.belt._


class ModularityTest extends org.scalatest.junit.JUnit3Suite {

    type not[b <: Boolean] = b#not
     def not(b: Boolean)/*: not[b.self]*/ = b.not.self

    type notNot[b <: Boolean] = not[not[b]]
     def notNot(b: Boolean)/*: notNot[b.self]*/ = not(not(b))

    type notNotNot[b <: Boolean] = not[not[not[b]]]
     def notNotNot(b: Boolean)/*: notNotNot[b.self]*/ = not(not(not(b)))

    type inc[n <: Nat] = n#increment
     def inc(n: Nat)/*: inc[n.self]*/ = n.increment.self

    type incInc[n <: Nat] = inc[inc[n]]
     def incInc(n: Nat)/*: incInc[n.self]*/ = inc(inc(n))

    def testTrivial {
        val b1: `true`.type = not(`false`)
        val b2: `false`.type = notNot(`false`)
        val b3: `true`.type = notNotNot(`false`)

        val i1: _2.type = incInc(_0)
        val i2: _3.type = incInc(_1)
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
