

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok
package belt


trait Function extends Self { g =>
    val dom: Type
    val cod: Type

    def apply(x: dom.!): cod.!

    final def compose(f: Function { val cod: g.self.dom.type }) = Function.compose(g.self)(f)
}


object Function {
    implicit def apply[a, b](f: a => b)(implicit ta: Type.Of[a], tb: Type.Of[b]) = new Function {
        override val dom: ta.type = ta
        override val cod: tb.type = tb
        override def apply(x: dom.!): cod.! = f(x)
    }

    private def compose(g: Function)(f: Function { val cod: g.dom.type }) = new Function {
        override val dom: f.dom.type = f.dom
        override val cod: g.cod.type = g.cod
        override def apply(x: dom.!): cod.! = g(f(x))
    }

    val f = Function((x: Int) => x.toString)
    val g = Function((x: String) => x(0))
    val k = g compose f
    val i: Char = k(3)

}
