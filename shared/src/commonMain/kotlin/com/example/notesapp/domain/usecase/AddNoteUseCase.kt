package com.example.notesapp.domain.usecase

import com.example.notesapp.data.repository.notes.NotesRepository
import com.example.notesapp.domain.note.Note

class AddNoteUseCase(private val repository: NotesRepository) {
    suspend fun saveNotes(note: Note) : Result<Boolean> {
        return try {
            repository.saveNotes(note)
            Result.success(true)
        } catch (ex: Exception) {
            ex.printStackTrace()
            Result.failure(ex)
        }
    }
}