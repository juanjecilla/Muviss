package com.scallop.muviss

import android.app.Application
import android.os.StrictMode
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppClass : Application() {
    override fun onCreate() {
        if (DEVELOPER_MODE) {
            StrictMode.setThreadPolicy(
                StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyFlashScreen()
                    .penaltyLog()
                    .build()
            )
            StrictMode.setVmPolicy(
                StrictMode.VmPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .penaltyDeath()
                    .build()
            )
        }
        super.onCreate()
    }

    companion object {
        const val DEVELOPER_MODE = true
    }
}