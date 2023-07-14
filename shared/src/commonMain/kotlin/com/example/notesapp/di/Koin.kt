package com.example.notesapp.di

import com.example.notesapp.data.remote.ApiClient
import com.example.notesapp.data.remote.ApiService
import com.example.notesapp.data.remote.ApiServiceImpl
import com.example.notesapp.presentation.quotes.QuotesViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        modules(networkModule(), appModule(), repositoryModule(), useCaseModule(), dispatcherModule, platformModule())
        appDeclaration()
    }

// IOS
fun initKoin() {
    startKoin {
        modules(networkModule(), appModule(), repositoryModule(), useCaseModule(), dispatcherModule, platformModule(), quotesModule)
    }
}

val dispatcherModule = module {
    factory { Dispatchers.Default }
}


fun appModule() = module {

    single {
        ApiClient.getClient(get())
    }

    single<ApiService> {
        ApiServiceImpl(get())
    }
}

val quotesModule = module {
    single {
        QuotesViewModel()
    }
}