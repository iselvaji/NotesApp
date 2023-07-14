package com.example.notesapp.presentation.quotes

import com.example.notesapp.presentation.base.UiEvent

sealed class QuoteListScreenEvent : UiEvent {
    object LoadQuotes : QuoteListScreenEvent()
}