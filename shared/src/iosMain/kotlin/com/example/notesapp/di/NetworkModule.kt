package com.example.notesapp.di

import io.ktor.client.engine.darwin.*
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun networkModule(): Module = module {
    single {
        Darwin.create()
    }
}