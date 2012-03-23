

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok
package belt


// A challenge to deprecate type-members

trait Type {
    type !
}

object Type {
    type Of[x] = Type {
        type ! = x
    }

    implicit object Int extends Type {
        override type ! = Int
    }

    implicit object Char extends Type {
        override type ! = Char
    }

    implicit object String extends Type {
        override type ! = String
    }
}
