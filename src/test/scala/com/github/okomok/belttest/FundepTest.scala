

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok.belttest


class FundepTest extends org.scalatest.junit.JUnit3Suite {

    trait KindFunc {
        type apply[+a]
    }

    object MyList extends KindFunc {
        override type apply[+a] = List[a]
    }
    trait MonadError[m[+_]] extends KindFunc {
        override type apply[+a] = m[a]
        type ErrorType
        def catchError[a](m: m[a])(h: ErrorType => m[a]): m[a]
    }

    object MonadError {
//        def apply[m <: KindFunc](implicit _M: MonadError[m#apply]): _M.type = _M
        def apply[m <: KindFunc](implicit _M: MonadError[m#apply]): MonadError[m#apply] { type ErrorType = _M.ErrorType } = _M
    }

    final case class ErrorT[e, n[+_], +a](val old: n[Either[e, a]])

    object ErrorT {
        trait apply2[e, n <: KindFunc] extends KindFunc {
            override type apply[+a] = ErrorT[e, n#apply, a]
        }

        implicit def _asMonadError[n[+_], e]: MonadError[({type L[+a] = ErrorT[e, n, a]})#L] { type ErrorType = e } = new MonadError[({type L[+a] = ErrorT[e, n, a]})#L] {
            type m[+a] = ErrorT[e, n, a]
            override type ErrorType = e
            override def catchError[a](m: m[a])(h: e => m[a]): m[a] = throw new Error("todo")
        }
    }

    def testTrivial {
        val _M = MonadError[ErrorT.apply2[String, MyList.type]]
        type m[+a] = _M.apply[a]
        import _M._
        implicitly[ErrorType =:= String]

        def foo(m: m[Int]) {
            val bar: String => m[Int] = _ => m
            catchError(m)((h: String) => bar(h))
        }
    }
}
