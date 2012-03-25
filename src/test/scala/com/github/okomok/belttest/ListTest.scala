

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok.belttest


import com.github.okomok.belt._


class ListTest extends org.scalatest.junit.JUnit3Suite {

    def cons(n: Nat, ns: List) = Cons(n, ns)

    def testCons {
        val ns = cons(_1, cons(_2, cons(_3, Nil)))
        checkTrue { ns.tail.tail.head.is_3 }
        checkTrue { ns.tail.head.is_2 }
        checkTrue { ns.head.is_1 }
    }

    def testLength {
        val tmp1 = cons(_3, Nil)
        val tmp2 = cons(_2, tmp1)
        val ns = cons(_1, tmp2)
        checkTrue { ns.length.is_3 }
    }
}
