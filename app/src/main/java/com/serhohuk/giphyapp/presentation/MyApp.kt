package com.serhohuk.giphyapp.presentation

import android.app.Application
import com.serhohuk.giphyapp.presentation.di.repoModule
import com.serhohuk.giphyapp.presentation.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(level = Level.ERROR)
            androidContext(this@MyApp)
            modules(listOf(repoModule, viewModelModule))
        }
    }
}