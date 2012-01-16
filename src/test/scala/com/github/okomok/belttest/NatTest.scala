

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok.belttest


import com.github.okomok.belt._
import Nat._


class NatTest extends org.scalatest.junit.JUnit3Suite {

    def mustBeTrue(x: `true`.type) = ()
    def mustBeFalse(x: `false`.type) = ()

    def inc(n: Nat) = n.increment.self
    def incInc(n: Nat) = inc(inc(n)).self

    def testTrivial {
        mustBeTrue { _0 equal _0 }
        mustBeTrue { _1 equal _1 }
        mustBeTrue { _2 equal _2 }

        mustBeTrue { incInc(_2) equal _4 }
        mustBeTrue { _3 equal incInc(_1) }

        mustBeFalse { _3 equal _0 }
        mustBeFalse { _0 equal _3 }

        mustBeFalse { incInc(_2) equal _3 }
        mustBeFalse { _2 equal incInc(_1) }
    }

    def testModular {
        def foo(n: Nat, m: Nat) = incInc(n).equal(m).self
        mustBeTrue { foo(_3, _5) }
        mustBeFalse { foo(_4, _5) }
    }
}
