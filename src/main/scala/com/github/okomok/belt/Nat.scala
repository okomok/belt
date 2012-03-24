

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok
package belt


sealed abstract class Nat extends Any {
    val decrement: Nat
    val increment: Nat

    val is_0: Boolean
    val is_1: Boolean
    val is_2: Boolean
    val is_3: Boolean
    val is_4: Boolean
    val is_5: Boolean
}


object _0 extends Nat {
    override lazy val decrement = throw new Error("underflow")
    override val increment = _1.self

    override val is_0 = `true`.self
    override val is_1 = `false`.self
    override val is_2 = `false`.self
    override val is_3 = `false`.self
    override val is_4 = `false`.self
    override val is_5 = `false`.self
}

object _1 extends Nat {
    override val decrement = _0.self
    override val increment = _2.self

    override val is_0 = `false`.self
    override val is_1 = `true`.self
    override val is_2 = `false`.self
    override val is_3 = `false`.self
    override val is_4 = `false`.self
    override val is_5 = `false`.self
}

object _2 extends Nat {
    override val decrement = _1.self
    override val increment = _3.self

    override val is_0 = `false`.self
    override val is_1 = `false`.self
    override val is_2 = `true`.self
    override val is_3 = `false`.self
    override val is_4 = `false`.self
    override val is_5 = `false`.self
}

object _3 extends Nat {
    override val decrement = _2.self
    override val increment = _4.self

    override val is_0 = `false`.self
    override val is_1 = `false`.self
    override val is_2 = `false`.self
    override val is_3 = `true`.self
    override val is_4 = `false`.self
    override val is_5 = `false`.self
}

object _4 extends Nat {
    override val decrement = _3.self
    override val increment = _5.self

    override val is_0 = `false`.self
    override val is_1 = `false`.self
    override val is_2 = `false`.self
    override val is_3 = `false`.self
    override val is_4 = `true`.self
    override val is_5 = `false`.self
}

object _5 extends Nat {
    override val decrement = _4.self
    override lazy val increment = throw new Error("overflow")

    override val is_0 = `false`.self
    override val is_1 = `false`.self
    override val is_2 = `false`.self
    override val is_3 = `false`.self
    override val is_4 = `false`.self
    override val is_5 = `true`.self
}
