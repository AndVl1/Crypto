package ru.bmstu.mobile.crypto

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    lateinit var instance: App
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}