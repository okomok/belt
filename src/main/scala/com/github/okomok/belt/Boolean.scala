

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok
package belt


sealed abstract class Boolean {
    type not <: Boolean
     val not: not
}


object True extends Boolean {
    override type not = False.type
    override  val not: not = False
}

object False extends Boolean {
    override type not = True.type
    override  val not: not = True
}
