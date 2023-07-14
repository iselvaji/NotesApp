package com.example.notesapp.di

import com.example.notesapp.domain.usecase.AddNoteUseCase
import com.example.notesapp.domain.usecase.NotesDetailsValidateUseCase
import com.example.notesapp.domain.usecase.NotesUseCase
import com.example.notesapp.domain.usecase.QuotesUseCase
import org.koin.dsl.module

fun useCaseModule() = module {

    single<QuotesUseCase> {
        QuotesUseCase(get())
    }

    single<NotesUseCase> {
        NotesUseCase(get())
    }

    single<AddNoteUseCase> {
        AddNoteUseCase(get())
    }

    single<NotesDetailsValidateUseCase> {
        NotesDetailsValidateUseCase()
    }
}