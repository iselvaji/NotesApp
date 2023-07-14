package com.example.notesapp.di

import com.example.notesapp.data.local.DatabaseDriverFactory
import com.example.notesapp.data.local.note.SqlDelightNoteDataSource
import com.example.notesapp.database.NoteDatabase
import com.example.notesapp.domain.note.NoteDataSource

object DatabaseModule {
    private val factory by lazy { DatabaseDriverFactory() }
    val noteDataSource: NoteDataSource by lazy {
        SqlDelightNoteDataSource(NoteDatabase(factory.createDriver()))
    }
}