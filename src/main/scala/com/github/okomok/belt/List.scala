

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok
package belt


sealed abstract class List extends Any {
    val head: Any
    val tail: List
    val isEmpty: Boolean
    val length: Nat
}


case object Nil extends List {
    override lazy val head = throw new Error("Nil.head")
    override lazy val tail = throw new Error("Nil.tail")
    override val isEmpty = `true`.self
    override val length: _0.type = _0.self
}

object Cons {
    def apply(x: Any, xs: List) = new List {
        override val head = x.self
        override val tail = xs.self
        override val isEmpty = `false`.self
        override val length = xs.length.increment.self
    }
}
