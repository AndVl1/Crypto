package ru.bmstu.mobile.crypto.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.Long

fun Long.toDate(): String {
    val date = Date(this)
    val format = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.US)
    return format.format(date)
}
