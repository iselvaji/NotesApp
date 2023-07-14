package com.example.notesapp.di

import io.ktor.client.engine.android.*
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun networkModule(): Module = module {

    single {
        Android.create()
    }
}