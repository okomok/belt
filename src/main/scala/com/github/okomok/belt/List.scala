

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok
package belt


sealed abstract class List extends Any { outer =>
    type head
     val head: head
    type tail <: List
     val tail: tail

    // Doesn't work for some reason.
    def ::(x: Any) = new List {
        override type head = x.self
        override  val head: head = x
        override type tail = outer.self
        override  val tail: tail = outer
    }
}


object Nil extends List {
    override type head = Nothing
    override lazy val head: head = throw new NoSuchElementException("Nil.head")
    override type tail = Nothing
    override lazy val tail: tail = throw new UnsupportedOperationException("Nil.tail")
}

object Cons {
    def apply(x: Any, xs: List) = new List {
        override type head = x.self
        override  val head: head = x
        override type tail = xs.self
        override  val tail: tail = xs
    }
}
