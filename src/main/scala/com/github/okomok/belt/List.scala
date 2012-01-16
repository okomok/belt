

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok
package belt


sealed abstract class List extends Any { outer =>
     val head  : Any
    type head <: Any
     val tail  : List
    type tail <: List

    // Doesn't work for some reason.
    def ::(x: Any) = new List {
        override  val head = x.self
        override type head = x.self
        override  val tail = outer.self
        override type tail = outer.self
    }
}


case object Nil extends List {
    override lazy val head = throw new NoSuchElementException("Nil.head")
    override type head = Nothing
    override lazy val tail = throw new UnsupportedOperationException("Nil.tail")
    override type tail = Nothing
}

object Cons {
    def apply(x: Any, xs: List) = new List {
        override  val head = x.self
        override type head = x.self
        override  val tail = xs.self
        override type tail = xs.self
    }
}
