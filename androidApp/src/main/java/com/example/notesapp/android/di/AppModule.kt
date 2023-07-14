package com.example.notesapp.android.di

import com.example.notesapp.android.util.ConnectivityNetworkMonitor
import com.example.notesapp.android.util.ConnectivityObserver
import com.example.notesapp.data.local.DatabaseDriverFactory
import com.example.notesapp.data.local.note.SqlDelightNoteDataSource
import com.example.notesapp.database.NoteDatabase
import com.example.notesapp.domain.note.NoteDataSource
import com.example.notesapp.presentation.noteDetails.NoteDetailViewModel
import com.example.notesapp.presentation.notes.NoteListViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

internal val AppModule = module {

    single {
        DatabaseDriverFactory(androidApplication().applicationContext).createDriver()
    }

    single {
        NoteDatabase(get())
    }

    single {
        SqlDelightNoteDataSource(get()) as NoteDataSource
    }

    single {
        NoteListViewModel()
    }

    single {
        NoteDetailViewModel()
    }

    single<ConnectivityObserver> {
        ConnectivityNetworkMonitor(androidApplication().applicationContext)
    }
}
