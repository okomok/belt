

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok
package belt


// x.type seems broken.
// Also, the type-inferencer needs explicit `self` calls.

trait Any { outer =>
    final  val self: self = this
    final type self       = this.type

     def asNat  : Nat = throw new Error("Any.asNat")
    type asNat <: Nat

     def asBoolean  : Boolean = throw new Error("Any.asBoolean")
    type asBoolean <: Boolean
}
