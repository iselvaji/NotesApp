package com.example.notesapp.android.noteDetails

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.notesapp.android.R
import com.example.notesapp.presentation.noteDetails.NoteDetailScreenEvent
import com.example.notesapp.presentation.noteDetails.NoteDetailState
import com.example.notesapp.presentation.noteDetails.NoteDetailViewModel
import com.example.notesapp.presentation.noteDetails.NoteDetailsUIEffect
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NoteDetailScreen(
    navController: NavController,
    viewModel: NoteDetailViewModel
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.effect.collectLatest {
            when(it) {
                NoteDetailsUIEffect.NavigateUp ->
                    navController.navigateUp()
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = colorResource(id = R.color.teal),
                title = {
                    Text(text = stringResource(R.string.add_note))
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Go Back")
                    }
                }
            )
        },
        content =  { NotesCreationUI(viewModel, state) }
    )
}

@Composable
fun NotesCreationUI(
    viewModel : NoteDetailViewModel,
    state: NoteDetailState
){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally)
    {

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = state.noteTitle,
            onValueChange = {
                viewModel.setEvent(NoteDetailScreenEvent.NoteTitleChanged(it))
            },
            label = { Text(stringResource(R.string.note_title)) }
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = state.noteContent,
            onValueChange = {
                viewModel.setEvent(NoteDetailScreenEvent.NoteContentChanged(it))
            },
            label = { Text(stringResource(R.string.note_content)) }
        )

        Spacer(modifier = Modifier.height(20.dp))

        if (state.inputValidationFailed) {
            Text(
                text = LocalContext.current.getString(R.string.provide_details),
                color = MaterialTheme.colors.error,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = { viewModel.setEvent(NoteDetailScreenEvent.SaveNote) },
            Modifier.padding(30.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.teal))) {
            Text(text = stringResource(R.string.save), color = Color.Black)
        }
    }
}