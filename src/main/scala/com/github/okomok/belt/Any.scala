

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok
package belt


trait Any {
    type self = this.type
    final val self: self = this
}
