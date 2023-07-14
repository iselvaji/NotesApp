package com.example.notesapp.android

import android.app.Application
import com.example.notesapp.android.di.AppModule
import com.example.notesapp.di.initKoin
import com.example.notesapp.di.quotesModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class NoteApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger()
            androidContext(this@NoteApp)
            modules(
                AppModule,
                quotesModule
            )
        }
    }
}