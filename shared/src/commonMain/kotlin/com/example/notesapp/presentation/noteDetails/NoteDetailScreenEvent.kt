package com.example.notesapp.presentation.noteDetails

import com.example.notesapp.presentation.base.UiEvent

sealed class NoteDetailScreenEvent : UiEvent {
    data class NoteTitleChanged(val title: String) : NoteDetailScreenEvent()
    data class NoteContentChanged(val content: String) : NoteDetailScreenEvent()
    object SaveNote : NoteDetailScreenEvent()
}