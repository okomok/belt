

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok
package belt


trait Function0 extends Any {
    type apply[a1 <: Any]
     def apply(a1: Any): apply[a1.self]
}
