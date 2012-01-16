

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok
package belt


sealed abstract class Nat extends Any {
    type decrement <: Nat
     val decrement: decrement

    type isZero <: Boolean
     val isZero: isZero

    type equal[that <: Nat] <: Boolean
    final def equal(that: Nat): equal[that.self] = _equal(that).asInstanceOf[equal[that.self]]
    private[belt] def _equal(that: Nat): Any // SI-5033 workaround

    final lazy val increment = Succ(self).self
}


private[belt] object Sentinel extends Nat {
    override type decrement = self
    override  val decrement: decrement = self

    override type isZero = `false`.self
    override  val isZero: isZero = `false`.self

    override type equal[that <: Nat] = `false`.self
    private[belt] override  def _equal(that: Nat): equal[that.self] = `false`.self
}


object Zero extends Nat {
    override type decrement = Sentinel.self
    override  val decrement: decrement = Sentinel.self

    override type isZero = `true`.self
    override  val isZero: isZero = `true`.self

    override type equal[that <: Nat] = that#isZero
    private[belt] override  def _equal(that: Nat): equal[that.self] = that.isZero
}


object Succ {
    def apply(n: Nat) = new Nat {
        override type decrement = n.self
        override  val decrement: decrement = n.self

        override type isZero = `false`.self
        override  val isZero: isZero = `false`.self

        override type equal[that <: Nat] = decrement#equal[that#decrement]
        private[belt] override  def _equal(that: Nat): equal[that.self] = decrement.equal(that.decrement).asInstanceOf[equal[that.self]]
    }
}


object Nat {
    val _0 = Zero
    val _1 = Succ(_0)
    val _2 = Succ(_1)
    val _3 = Succ(_2)
    val _4 = Succ(_3)
    val _5 = Succ(_4)
    val _6 = Succ(_5)
    val _7 = Succ(_6)
    val _8 = Succ(_7)
    val _9 = Succ(_8)
}
