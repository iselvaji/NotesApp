package com.example.notesapp.android

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.navigation.compose.rememberNavController
import com.example.notesapp.android.navigation.BottomNavigationBar
import com.example.notesapp.android.navigation.NavigationMap

class MainActivity : ComponentActivity() {

   /* private val viewModelNotes: NoteListViewModel by inject()
    private val viewModelNotesDetails: NoteDetailViewModel by inject()
    private val viewModelQuotes: QuotesViewModel by inject()*/

   /* override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Route.SCREEN_QUOTES
                       // startDestination = Route.SCREEN_NOTES_LIST
                    ) {
                        composable(Route.SCREEN_NOTES_LIST) {
                            NoteListScreen(navController, viewModelNotes)
                        }
                        composable(Route.SCREEN_NOTES_DETAILS) {
                            NoteDetailScreen(-1, navController, viewModelNotesDetails)
                        }
                        composable(Route.SCREEN_QUOTES) {
                            QuotesListScreen(viewModelQuotes)
                        }
                    }
                }
            }
        }
    }*/

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = { BottomNavigationBar(navController) }
                ) {
                    NavigationMap(navController = navController)
                }
            }
        }
    }
}

