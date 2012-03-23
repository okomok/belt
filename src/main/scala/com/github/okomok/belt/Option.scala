

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok
package belt


trait Option {
    def isEmpty: Boolean
//    val get: Type
    val get: Any // get.!
}

case object None extends Option {
    override def isEmpty = true
    override lazy val get = throw new Error
}

object Some {
    def apply(x: AnyRef) = new Option {
        override def isEmpty = false
        override val get: x.type = x
    }
}
