package com.example.notesapp.presentation.noteDetails

import com.example.notesapp.presentation.base.UiEffect

sealed class NoteDetailsUIEffect : UiEffect {
    object NavigateUp : NoteDetailsUIEffect()
}