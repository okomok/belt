

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok
package belt


// Dependet-method-type computational model seems to lack if-statement.


sealed abstract class Boolean extends Any {
    final override  val asBoolean = self
    final override type asBoolean = self

     val not  : Boolean
    type not <: Boolean

    type `if`[then <: Function0, _else <: Function0] <: Function0
    final def `if`(then: Function0, _else: Function0): `if`[then.self, _else.self] = _if(then, _else).asInstanceOf[`if`[then.self, _else.self]]
    private[belt] def _if(then: Function0, _else: Function0): Any
}


object `true` extends Boolean {
    override  val not = `false`.self
    override type not = `false`.self

    override type `if`[then <: Function0, _else <: Function0] = then
    private[belt] override def _if(then: Function0, _else: Function0): Any = then

}

object `false` extends Boolean {
    override  val not = `true`.self
    override type not = `true`.self

    override type `if`[then <: Function0, _else <: Function0] = _else
    private[belt] override def _if(then: Function0, _else: Function0): Any = _else

}
