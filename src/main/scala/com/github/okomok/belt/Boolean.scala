

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok
package belt


sealed abstract class Boolean extends Any {
    val not: Boolean

    val and_true: Boolean
    val and_false: Boolean

    val or_true: Boolean
    val or_false: Boolean
}


object `true` extends Boolean {
    override val not = `false`.self

    override val and_true = self
    override val and_false = `false`.self

    override val or_true = self
    override val or_false = self
}

object `false` extends Boolean {
    override val not = `true`.self

    override val and_true = self
    override val and_false = self

    override val or_true = `true`.self
    override val or_false = self
}
