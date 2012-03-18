

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok.belttest


class TrivialTest extends org.scalatest.junit.JUnit3Suite {

    class Foo extends MyAny {
        def foo: Unit = ()
    }

    trait MyAny {
        type self = this.type
        val self: self = this
    }

    object Mistery {
        def who(x: AnyRef): x.type = x

        def testTrivial {
            who(new Foo).foo // no

            val f = new Foo
            who(f).foo // ok
        }
    }

    object Mystery2 {

        trait Value {
            type value
            val value: value
        }

        type ValueOf[v] = Value { type value = v }

        def who(x: AnyRef): ValueOf[x.type] = new Value {
            type value = x.type
            val value = x
        }

        def who2(x: MyAny): ValueOf[x.self] = new Value {
            type value = x.self
            val value = x.self
        }

        def testTrivial2 {
            val f = new Foo
            who(f).value.foo

            who(new Foo).value.foo // no

            who2(new Foo).value.foo
        }
    }
}
