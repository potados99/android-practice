package com.potados.practice

import android.app.Application
import android.content.Context
import com.potados.practice.di.appModule
import org.koin.android.ext.android.startKoin

class MyApp : Application() {
    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext

        startKoin(this, listOf(appModule))
    }
}