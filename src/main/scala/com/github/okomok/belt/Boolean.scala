

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok
package belt


// Dependet-method-type computational model seems to lack if-statement.


sealed abstract class Boolean extends Any {
    type not <: Boolean
     val not: not
/*
    type `if`[then <: Function0, _else <: Function0] <: Function0
     def `if`(then: Function0, _else: Function0): `if`[then.self, _else.self]
*/
}


object `true` extends Boolean {
    override type not = `false`.type
    override  val not: not = `false`
/*
    override type `if`[then <: Function0, _else <: Function0] = then
    override  def `if`(then: Function0, _else: Function0): `if`[then.self, _else.self] = then
*/
}

object `false` extends Boolean {
    override type not = `true`.type
    override  val not: not = `true`
/*
    override type `if`[then <: Function0, _else <: Function0] = _else
    override  def `if`(then: Function0, _else: Function0): `if`[then.self, _else.self] = _else
*/
}
