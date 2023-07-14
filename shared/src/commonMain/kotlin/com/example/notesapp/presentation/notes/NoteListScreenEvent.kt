package com.example.notesapp.presentation.notes

import com.example.notesapp.presentation.base.UiEvent

sealed class NoteListScreenEvent : UiEvent {
    object LoadNotes : NoteListScreenEvent()
    data class DeleteNotes(val noteId: Long) : NoteListScreenEvent()
    object SyncNotes : NoteListScreenEvent()
}