

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok
package belt


sealed abstract class List {
    type head
     val head: head
    type tail <: List
     val tail: tail

    type self = this.type
     val self: self = this

    // Doesn't work for some reason.
    def ::(x: Any) = new List {
        override type head = x.type
        override  val head: head = x
        override type tail = self
        override  val tail: tail = self
    }
}


object Nil extends List {
    override type head = Nothing
    override lazy val head: head = throw new NoSuchElementException("Nil.head")
    override type tail = Nothing
    override lazy val tail: tail =  throw new UnsupportedOperationException("Nil.tail")
}


object Cons {
    def apply(x: Any, xs: List) = new List {
        override type head = x.type
        override  val head: head = x
        override type tail = xs.type
        override  val tail: tail = xs
    }
}
