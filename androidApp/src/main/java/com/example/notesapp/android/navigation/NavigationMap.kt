package com.example.notesapp.android.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.notesapp.android.noteDetails.NoteDetailScreen
import com.example.notesapp.android.notes.NoteListScreen
import com.example.notesapp.android.quotes.QuotesListScreen
import org.koin.androidx.compose.get

@Composable
fun NavigationMap(navController: NavHostController) {

    NavHost(navController, startDestination = BottomNavItem.Notes.route) {
        composable(BottomNavItem.Notes.route) {
           NoteListScreen(navController = navController, viewModel = get(), connectivityObserver = get())
        }
        composable(BottomNavItem.Quotes.route) {
            QuotesListScreen(get())
        }
        composable(Screen.NotesDetails.route) {
            NoteDetailScreen(navController, get())
        }
    }
}