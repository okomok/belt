

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok
package belt


// x.type seems broken.
// Also, the type-inferencer needs explicit `self` calls.

trait Any {
    type self = this.type
     val self: self = this
}
