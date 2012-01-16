

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok
package belt


sealed abstract class Nat extends Any {
    final override  val asNat = self
    final override type asNat = self

     val decrement  : Nat
    type decrement <: Nat

     val isZero  : Boolean
    type isZero <: Boolean

    final lazy val increment = Succ(self)
    final type increment     = Succ[self]

     def equal(that  : Nat)  : Boolean
    type equal[that <: Nat] <: Boolean

     def foldRight(z  : Any, f  : Function2)  : Any
    type foldRight[z <: Any, f <: Function2] <: Any

    final  def plus(that: Nat): plus[that.self] = that.foldRight(self, new Function2 {
        override  def apply(n  : Any, z  : Any) = z.asNat.increment.self
        override type apply[n <: Any, z <: Any] = z#asNat#increment#self
    }).asNat.asInstanceOf[plus[that.self]]

    final type plus[that <: Nat] = that#foldRight[self, Function2 {
        type apply[n <: Any, z <: Any] = z#asNat#increment
    }]#asNat

//    type plus[that <: Nat] <: Boolean
//    final def plus(that: Nat): plus[that.self] = _plus(that).asInstanceOf[plus[that.self]]
//   private[belt] def _plus(that: Nat): Any
}


case object Zero extends Nat {
    override lazy val decrement = throw new Error("underflow")
    override type decrement     = Nothing

    override  val isZero = `true`.self
    override type isZero = `true`.self

    override  def equal(that  : Nat): equal[that.type] = that.isZero.asInstanceOf[equal[that.type]]
    override type equal[that <: Nat]                   = that#isZero

    override  def foldRight(z  : Any, f  : Function2): foldRight[z.type, f.type] = z.asInstanceOf[foldRight[z.type, f.type]]
    override type foldRight[z <: Any, f <: Function2]                            = z
}


final case class Succ[n <: Nat](n: n) extends Nat {
    override  val decrement = n.self
    override type decrement = n.self

    override  val isZero = `false`.self
    override type isZero = `false`.self

    override  def foldRight(z  : Any, f  : Function2): foldRight[z.type, f.type] = f.apply(self, decrement.foldRight(z, f)).asInstanceOf[foldRight[z.type, f.type]]
    override type foldRight[z <: Any, f <: Function2]                            = f#apply[self, decrement#foldRight[z, f]]

    override  def equal(that  : Nat): equal[that.type] = that.isZero.`if`(const(`false`), new Function0 {
        override  def apply = decrement.equal(that.self.decrement).self
        override type apply = decrement#equal[that.self#decrement]#self
    }).apply.asBoolean.asInstanceOf[equal[that.type]]
    override type equal[that <: Nat] = that#isZero#`if`[const[`false`.type], Function0 {
        type apply = decrement#equal[that#self#decrement]
    }]#apply#asBoolean
}


object Nat {
    val _0 = Zero.self
    val _1 = Succ(_0).self
    val _2 = Succ(_1).self
    val _3 = Succ(_2).self
    val _4 = Succ(_3).self
    val _5 = Succ(_4).self
    val _6 = Succ(_5).self
    val _7 = Succ(_6).self
    val _8 = Succ(_7).self
    val _9 = Succ(_8).self

    type _0 = _0.type
    type _1 = _1.type
    type _2 = _2.type
    type _3 = _3.type
    type _4 = _4.type
    type _5 = _5.type
    type _6 = _6.type
    type _7 = _7.type
    type _8 = _8.type
    type _9 = _9.type
}




/*
object Succ {
    def apply(n: Nat) = new Nat {
        override type decrement = n.self
        override  val decrement: decrement = n.self

        override type isZero = `false`.self
        override  val isZero: isZero = `false`.self

        private[belt] override def _foldRight(z: Any, f: Function2): Any = f.apply(self, decrement.foldRight(z, f))
        override type foldRight[z <: Any, f <: Function2] = f#apply[self, decrement#foldRight[z, f]]

        private[belt] override def _equal(that: Nat): equal[that.self] = decrement.equal(that.decrement).asInstanceOf[equal[that.self]]
        override type equal[that <: Nat] = decrement#equal[that#decrement]

//        override type plus[that <: Nat] = decrement#plus[that#increment]
//        private[belt] override def _plus(that: Nat): Any = decrement.plus(that.increment)
    }
}
*/


/*

private[belt] case object Sentinel extends Nat {
    override  val decrement = self
    override type decrement = self

    override  val isZero = `false`.self
    override type isZero = `false`.self

    override  def equal(that  : Nat) = `false`.self
    override type equal[that <: Nat] = `false`.self
/*
    private[belt] override def _foldRight(z: Any, f: Function2): Any = throw new Error("impossible")
    override type foldRight[z <: Any, f <: Function2] = Nothing
*/

//    override type plus[that <: Nat] = Nothing
//    private[belt] override def _plus(that: Nat): Any = throw new Error("impossible")
}

*/
