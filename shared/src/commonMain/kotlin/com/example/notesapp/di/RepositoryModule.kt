package com.example.notesapp.di

import com.example.notesapp.data.repository.notes.NotesRepository
import com.example.notesapp.data.repository.notes.NotesRepositoryImpl
import com.example.notesapp.data.repository.quotes.QuotesRepository
import com.example.notesapp.data.repository.quotes.QuotesRepositoryImpl
import org.koin.dsl.module

fun repositoryModule() = module {

    single<QuotesRepository> {
        QuotesRepositoryImpl(get())
    }

    single<NotesRepository> {
        NotesRepositoryImpl(get(), get())
    }
}