

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok
package belt


sealed abstract class Nat extends Any {
    final override  val asNat: asNat = self
    final override type asNat = self

     val decrement: decrement
    type decrement <: Nat

     val isZero: isZero
    type isZero <: Boolean

    final lazy val increment: increment = Succ(self)
    final type increment = Succ[self]

    final def foldRight(z: Any, f: Function2): foldRight[z.self, f.self] = _foldRight(z, f).asInstanceOf[foldRight[z.self, f.self]]
    type foldRight[z <: Any, f <: Function2] <: Any
    private[belt] def _foldRight(z: Any, f: Function2): Any

    final def equal(that: Nat): equal[that.self] = _equal(that).asInstanceOf[equal[that.self]]
    type equal[that <: Nat] <: Boolean
    private[belt] def _equal(that: Nat): Any // SI-5033 workaround

    final  def plus(that: Nat): plus[that.self] = that.foldRight(self, new Function2 {
        override def _apply(n: Any, z: Any): apply[n.self, z.self] = z.asNat.increment
        override type apply[n <: Any, z <: Any]                    = z#asNat#increment
    }).asNat.asInstanceOf[plus[that.self]]

    final type plus[that <: Nat] = that#foldRight[self, Function2 {
        type apply[n <: Any, z <: Any] = z#asNat#increment
    }]#asNat

//    type plus[that <: Nat] <: Boolean
//    final def plus(that: Nat): plus[that.self] = _plus(that).asInstanceOf[plus[that.self]]
//   private[belt] def _plus(that: Nat): Any
}


/*
private[belt] object Sentinel extends Nat {
    override  val decrement: decrement = self
    override type decrement            = self

    override  val isZero: isZero = `false`.self
    override type isZero         = `false`.self

    private[belt] override def _foldRight(z: Any, f: Function2): Any = throw new Error("impossible")
    override type foldRight[z <: Any, f <: Function2] = Nothing

    private[belt] override def _equal(that: Nat): Any = `false`.self
    override type equal[that <: Nat]                  = `false`.self

//    override type plus[that <: Nat] = Nothing
//    private[belt] override def _plus(that: Nat): Any = throw new Error("impossible")
}
*/

case object Zero extends Nat {
    override lazy val decrement: decrement = throw new Error("underflow")
    override type decrement = Nothing

    override  val isZero: isZero = `true`.self
    override type isZero = `true`.self

    private[belt] override def _foldRight(z: Any, f: Function2): Any = z.self
    override type foldRight[z <: Any, f <: Function2] = z

    private[belt] override def _equal(that: Nat): Any = that.isZero
    override type equal[that <: Nat]                  = that#isZero

//    override type plus[that <: Nat] = that.self
//    private[belt] override def _plus(that: Nat): Any = that.self
}


final case class Succ[n <: Nat](n: n) extends Nat {
    override  val decrement: decrement = n.self
    override type decrement = n.self

    override  val isZero: isZero = `false`.self
    override type isZero         = `false`.self

    private[belt] override def _foldRight(z: Any, f: Function2): Any = f.apply(self, decrement.foldRight(z, f))
    override type foldRight[z <: Any, f <: Function2]                = f#apply[self, decrement#foldRight[z, f]]

    private[belt] override def _equal(that: Nat): Any = that.isZero.`if`(const(`false`), new Function0 {
        override  def apply: apply = decrement.equal(that.self.decrement).asInstanceOf[apply]
        override type apply        = decrement#equal[that.self#decrement]
    }).apply.asBoolean
    override type equal[that <: Nat]                  = that#isZero#`if`[const[`false`.type], Function0 {
        type apply = decrement#equal[that#self#decrement]
    }]#apply#asBoolean

    //        override type plus[that <: Nat] = decrement#plus[that#increment]
    //        private[belt] override def _plus(that: Nat): Any = decrement.plus(that.increment)

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
}
