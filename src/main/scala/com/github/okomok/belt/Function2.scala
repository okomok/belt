

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok
package belt


trait Function2 extends Any {
    final def apply(a1: Any, a2: Any): apply[a1.self, a2.self] = _apply(a1, a2).asInstanceOf[apply[a1.self, a2.self]]
    type apply[a1 <: Any, a2 <: Any] <: Any
    protected def _apply(a1: Any, a2: Any): Any
}
