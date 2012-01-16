

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok


package object belt {

    // Doesn't work well...
    def `if`(b: Boolean, then: Function0, _else: Function0): b.`if`[then.self, _else.self] = b.`if`(then, _else).self

    type const[x <: Any] = Function0 { type apply = x }
    def const(x: Any): const[x.self] = new Function0 {
        override type apply = x.self
        override  def apply: apply = x.self
    }
}
