package com.example.notesapp.presentation.noteDetails

import com.example.notesapp.presentation.base.UiState

data class NoteDetailState(
    val noteTitle: String = "",
    val noteContent: String = "",
    val inputValidationFailed: Boolean = false
): UiState