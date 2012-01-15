

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok.belttest


class SI2712WorkaroundTest extends org.scalatest.junit.JUnit3Suite {

    trait Monad[m[+a]]

    object Monad {
        def _useMonad[m[+_], a](m: m[a])(implicit _M: Monad[m]): m[a] = m
        def useMonad[ma](m: ma)(implicit _M: Instance1[Monad, ma]): _M.apply1[_M.arg1] = _M(m)

//        implicit def _ofId: Monad[({type L[+a] = a})#L] = new Monad[({type L[+a] = a})#L]{}
    }

    trait Instance1[tc[_[+_]], fa] {
        type apply1[+a]
        type arg1
        def instance: tc[apply1]
        final def apply(fa: fa): apply1[arg1] = fa.asInstanceOf[apply1[arg1]]
    }

    object Instance1 {
/*
        implicit def _of0[tc[_[+_]], f](implicit _T: tc[({type L[+a] = f})#L]) = new Instance1[tc, f] {
            type apply1[+a] = f
            type arg1 = Nothing
            override def instance: tc[apply1] = _T
        }
*/
        implicit def _of1[tc[_[+_]], f[+_], a1](implicit _T: tc[f]) = new Instance1[tc, f[a1]] {
            type apply1[+a] = f[a]
            type arg1 = a1
            override def instance: tc[apply1] = _T
        }

        implicit def _of2[tc[_[+_]], f[_, +_], a1, a2](implicit _T: tc[({type L[+a] = f[a1, a]})#L]) = new Instance1[tc, f[a1, a2]] {
            type apply1[+a] = f[a1, a]
            type arg1 = a2
            override def instance: tc[apply1] = _T
        }
    }

    class MyParser[s, +a]

    object MyParser {
        implicit def _asMonad[s]: Monad[({type L[+a] = MyParser[s, a]})#L] = new Monad[({type L[+a] = MyParser[s, a]})#L]{}
    }

    def testTrivial {
        val p = new MyParser[String, Int]
        Monad.useMonad(p)
    }
}
