package ru.bmstu.mobile.crypto.di

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class Resources @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun getString(id: Int): String {
        return context.getString(id)
    }
}
