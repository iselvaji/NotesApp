package com.example.notesapp.domain.usecase

import com.example.notesapp.data.repository.notes.NotesRepository
import com.example.notesapp.domain.note.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NotesUseCase(private val repository: NotesRepository) {

    suspend fun getNotes(): Flow<List<Note>> = flow {
        try {
            val data = repository.getNotes()
            emit(data)
        } catch (ex: Exception) {
            emit(emptyList())
        }
    }

    suspend fun deleteNoteById(id: Long) {
        try {
           repository.deleteNoteById(id)
        } catch (ex: Exception) {
           ex.printStackTrace()
        }
    }

    suspend fun syncNotes() : Flow<List<Note>> = flow {
        try {
            emit(repository.syncNotes())
        } catch (ex: Exception) {
            emit(emptyList())
        }
    }
}