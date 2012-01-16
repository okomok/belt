

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok
package belt


sealed abstract class Boolean extends Any {
    type not <: Boolean
     val not: not
/*
    type `if`[then <: Function0, _else <: Function0] <: Function0
     def `if`(then: Function0, _else: Function0): `if`[then.self, _else.self]
*/
}


object True extends Boolean {
    override type not = False.type
    override  val not: not = False
/*
    override type `if`[then <: Function0, _else <: Function0] = then
    override  def `if`(then: Function0, _else: Function0): `if`[then.self, _else.self] = then
*/
}

object False extends Boolean {
    override type not = True.type
    override  val not: not = True
/*
    override type `if`[then <: Function0, _else <: Function0] = _else
    override  def `if`(then: Function0, _else: Function0): `if`[then.self, _else.self] = _else
*/
}
