

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok.belttest


import com.github.okomok.belt._


class NatTest extends org.scalatest.junit.JUnit3Suite {

    def inc(n: Nat) = n.increment.self
    def incInc(n: Nat) = inc(inc(n))

    def testInc {
        checkTrue { incInc(_0).is_2 }
        checkTrue { incInc(_1).is_3 }
        checkTrue { incInc(_2).is_4 }
    }
}
