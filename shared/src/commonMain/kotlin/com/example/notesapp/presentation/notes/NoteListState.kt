package com.example.notesapp.presentation.notes

import com.example.notesapp.domain.note.Note
import com.example.notesapp.presentation.base.UiState

data class NoteListState(
    val notes: List<Note> = emptyList()
): UiState