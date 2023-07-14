package com.example.notesapp.android.notes

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.notesapp.android.R
import com.example.notesapp.android.navigation.Screen
import com.example.notesapp.android.util.ConnectivityObserver
import com.example.notesapp.domain.note.Note
import com.example.notesapp.presentation.base.BasicUiState
import com.example.notesapp.presentation.notes.NoteListScreenEvent
import com.example.notesapp.presentation.notes.NoteListViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged

@Composable
fun NoteListScreen(
    navController: NavController,
    viewModel: NoteListViewModel,
    connectivityObserver: ConnectivityObserver
) {

    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = connectivityObserver) {
        connectivityObserver.isOnline.distinctUntilChanged().collectLatest {
            if(it) {
                viewModel.setEvent(NoteListScreenEvent.SyncNotes)
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.setEvent(NoteListScreenEvent.LoadNotes)
    }

    ManageResourceState(state,
        navigateToDetails = {
            navController.navigate(Screen.NotesDetails.route)
        },
        deleteNoteClicked = {
            viewModel.setEvent(NoteListScreenEvent.DeleteNotes(it))
        }
    )
}

@Composable
fun ManageResourceState(
    state: BasicUiState<List<Note>>,
    navigateToDetails: () -> Unit,
    deleteNoteClicked : (Long) -> Unit
) {
    when(state) {
        BasicUiState.Loading -> ShowProgress()
        is BasicUiState.Error -> ShowErrorMessage(state.message)
        is BasicUiState.Success -> ShowNotesList(
            state.data,
            navigateToDetails = {
               navigateToDetails()
            },
            deleteNoteClicked = {
               deleteNoteClicked(it)
            })
        BasicUiState.Idle -> {}
    }
}

@Composable
fun ShowNotesList(notes : List<Note>, navigateToDetails: () -> Unit, deleteNoteClicked : (Long) -> Unit) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.offset(x = (-10).dp,  y = (-70).dp),
                onClick = { navigateToDetails() },
                backgroundColor = colorResource(id = R.color.teal)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.add_note),
                    tint = Color.White
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(
                    items = notes,
                    key = { it.id!! }
                ) { note ->
                    NoteItem(
                        note = note,
                        onDeleteClick = {
                            deleteNoteClicked(note.id ?: 0L)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
fun ShowProgress() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ShowErrorMessage(message : String?) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (message != null) {
            Text(message)
        }
    }
}