package ru.bmstu.mobile.crypto.extensions

import android.content.Context
import android.content.Intent
import android.net.Uri

fun Context.openLink(link: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
    startActivity(intent)
}