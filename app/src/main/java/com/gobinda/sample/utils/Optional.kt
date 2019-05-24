package com.gobinda.test.utils

import java.util.NoSuchElementException

/**
 * Optional Class to handle Null
 */

class Optional<M>(private val optional: M?) {

    val isEmpty: Boolean
        get() = this.optional == null

    fun get(): M {
        if (optional == null) {
            throw NoSuchElementException("No value present")
        }
        return optional
    }

}
