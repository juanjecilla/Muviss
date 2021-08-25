package com.scallop.muviss

import android.app.Application
import com.scallop.muviss.di.networkModule
import com.scallop.muviss.di.repositoryModule
import com.scallop.muviss.di.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppClass : Application() {

    override fun onCreate() {
        super.onCreate()
        loadKoin()
    }

    private fun loadKoin() {
        startKoin {
            androidContext(this@AppClass)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                )
            )
        }
    }
}