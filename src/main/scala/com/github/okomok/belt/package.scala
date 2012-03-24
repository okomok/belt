

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok


package object belt {
    def checkEq(x: Any)(y: x.type) = Unit.self

    def checkTrue(x: `true`.type) = Unit.self
    def checkFalse(x: `true`.type) = Unit.self
}
