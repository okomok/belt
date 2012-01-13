

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok
package belt


trait Singleton extends Value {
    override type value = this.type
    override  val value: value = this
}
