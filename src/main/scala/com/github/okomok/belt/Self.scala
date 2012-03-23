

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok
package belt


// This indirection is sometimes needed for some reason.

trait Self {
    final protected val self: this.type = this
}
