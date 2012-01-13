

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok
package belt


sealed abstract class Nat {
    type increment <: Nat
     val increment: increment

    type decrement <: Nat
     val decrement: decrement

    type isZero <: Boolean
     val isZero: isZero
}


object _0 extends Nat {
    override type increment = _1.type
    override  val increment: increment = _1

    override type decrement = Nothing
    override lazy val decrement: decrement = throw new Error("underflow")

    override type isZero = True.type
    override  val isZero: isZero = True
}

object _1 extends Nat {
    override type increment = _2.type
    override  val increment: increment = _2

    override type decrement = _0.type
    override  val decrement: decrement = _0

    override type isZero = False.type
    override  val isZero: isZero = False
}

object _2 extends Nat {
    override type increment = _3.type
    override  val increment: increment = _3

    override type decrement = _1.type
    override  val decrement: decrement = _1

    override type isZero = False.type
    override  val isZero: isZero = False
}

object _3 extends Nat {
    override type increment = _4.type
    override  val increment: increment = _4

    override type decrement = _2.type
    override  val decrement: decrement = _2

    override type isZero = False.type
    override  val isZero: isZero = False
}

object _4 extends Nat {
    override type increment = _5.type
    override  val increment: increment = _5

    override type decrement = _3.type
    override  val decrement: decrement = _3

    override type isZero = False.type
    override  val isZero: isZero = False
}

object _5 extends Nat {
    override type increment = Nothing
    override lazy val increment: increment = throw new Error("overflow")

    override type decrement = _4.type
    override  val decrement: decrement = _4

    override type isZero = False.type
    override  val isZero: isZero = False
}
