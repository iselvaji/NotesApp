package com.example.notesapp.android.quotes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.notesapp.data.remote.model.Quote
import com.example.notesapp.presentation.base.BasicUiState
import com.example.notesapp.presentation.quotes.QuoteListScreenEvent
import com.example.notesapp.presentation.quotes.QuotesViewModel

@Composable
fun QuotesListScreen(viewModel: QuotesViewModel) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.setEvent(QuoteListScreenEvent.LoadQuotes)
    }

    ManageResourceState(state)
}

@Composable
fun ManageResourceState(state: BasicUiState<List<Quote>>,) {
    when(state) {
        BasicUiState.Loading -> ShowProgress()
        is BasicUiState.Error -> ShowErrorMessage(state.message)
        is BasicUiState.Success -> {
            Scaffold { padding ->
                Column(
                    modifier = Modifier.padding(padding)
                ) {
                    LazyColumn {
                        items(
                            items = state.data
                        ) { quote ->
                            QuotesItem(quote = quote)
                        }
                    }
                }
            }
        }
        BasicUiState.Idle -> {}
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
