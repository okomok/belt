

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok
package belt


// x.type seems broken.

trait Any {
    type self = this.type
     val self: self = this
}
