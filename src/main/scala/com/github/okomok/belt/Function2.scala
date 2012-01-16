

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok
package belt


trait Function2 extends Any {
     def apply(a1  : Any, a2  : Any)  : Any
    type apply[a1 <: Any, a2 <: Any] <: Any
}
