package com.gobinda.sample.utils

class Event<T>(private val content: T) {

    private var handled = false

    val contentIfNotHandled: T?
        get() {
            if (handled) {
                return null
            } else {
                handled = true
                return content
            }
        }

    init {
        if (content == null) {
            throw IllegalArgumentException("Null values in Event are not allowed.")
        }
    }

    fun isHandled(): Boolean {
        return handled
    }
}