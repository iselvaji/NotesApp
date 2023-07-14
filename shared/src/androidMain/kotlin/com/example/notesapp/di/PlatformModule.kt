package com.example.notesapp.di

import com.example.notesapp.presentation.base.MainDispatcher
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single { MainDispatcher() }
}