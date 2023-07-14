package com.example.notesapp.android.navigation

sealed class Screen(val route: String) {
    object Notes : Screen("notes_list")
    object NotesDetails : Screen("notes_details")
    object Quotes : Screen("quotes")
}