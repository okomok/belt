

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok.belttest


class SI5033WorkaroundTest extends org.scalatest.junit.JUnit3Suite {

    trait X {
        type foo
    }

    trait Base {
        final def bar(x: X): x.foo = _bar(x).asInstanceOf[x.foo]
        def _bar(x: X): Any
    }

    trait Derived extends Base {
        override def _bar(x: X): x.foo = throw new Error
    }

    def testTrivial {
    }

}
