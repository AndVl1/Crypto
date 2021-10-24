package ru.bmstu.mobile.crypto.base

interface IntentHandler<T> {
    fun handleIntent(event: T)
}
