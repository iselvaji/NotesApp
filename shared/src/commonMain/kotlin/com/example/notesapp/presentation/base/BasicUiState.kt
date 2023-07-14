package com.example.notesapp.presentation.base

sealed interface BasicUiState<out T> : UiState {
    data class Success<T>(val data: T) : BasicUiState<T>
    data class Error(val message: String? = null) : BasicUiState<Nothing>
    object Loading : BasicUiState<Nothing>
    object Idle : BasicUiState<Nothing>
}