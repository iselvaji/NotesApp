package com.example.notesapp.domain.usecase

import com.example.notesapp.domain.note.Note

class NotesDetailsValidateUseCase {
    fun isValid(note: Note) : Boolean {
        return note.title.isNotEmpty() && note.content.isNotEmpty()
    }
}