

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok.belttest


import com.github.okomok.belt._


class BooleanTest extends org.scalatest.junit.JUnit3Suite {

    def not(b: Boolean) = b.not.self

    def notNot(b: Boolean) = not(not(b))

    def notNotNot(b: Boolean) = not(not(not(b)))

    def testNot {
        checkEq(`true`)(`false`.not)
        checkEq(`false`)(`true`.not)

        checkEq(`true`) { not(`false`) }
        checkEq(`false`) { notNot(`false`) }
        checkEq(`true`) { notNotNot(`false`) }
    }
}
