package com.example.notesapp.data.repository.notes

import com.example.notesapp.domain.note.Note

interface NotesRepository {
    suspend fun getNotes() : List<Note>
    suspend fun deleteNoteById(id: Long)
    suspend fun saveNotes(note: Note)
    suspend fun syncNotes() : List<Note>
}