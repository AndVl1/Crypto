package ru.bmstu.mobile.crypto.di

import android.content.Context
import androidx.preference.PreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppPreferences @Inject constructor(@ApplicationContext context: Context) {

    val prefs = PreferenceManager.getDefaultSharedPreferences(context)

    fun putString(key: String, value: String) {
        prefs.edit().putString(key, value).apply()
    }

    fun getString(key: String): String {
        return getNullableString(key) ?: ""
    }

    fun getNullableString(key: String): String? {
        return prefs.getString(key, null)
    }
}
