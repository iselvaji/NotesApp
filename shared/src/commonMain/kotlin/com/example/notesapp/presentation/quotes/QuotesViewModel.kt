package com.example.notesapp.presentation.quotes

import com.example.notesapp.data.remote.model.Quote
import com.example.notesapp.domain.usecase.QuotesUseCase
import com.example.notesapp.presentation.base.BaseViewModel
import com.example.notesapp.presentation.base.BasicUiState
import com.example.notesapp.presentation.base.UiEffect
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.koin.core.component.inject

open class QuotesViewModel : BaseViewModel<QuoteListScreenEvent, BasicUiState<List<Quote>>, UiEffect>() {

    private val useCase: QuotesUseCase by inject()

    override fun createInitialState(): BasicUiState<List<Quote>> = BasicUiState.Idle

    override fun handleEvent(event: QuoteListScreenEvent) {
        when (event) {
            is QuoteListScreenEvent.LoadQuotes -> {
                getQuotes()
            }
        }
    }

    private fun getQuotes() {
        launch {
            useCase.getQuotes()
                .onStart {
                    setState { BasicUiState.Loading }
                }.catch {
                    setState {
                        BasicUiState.Error(it.message ?: "Something went wrong!")
                    }
                }.collectLatest {
                    setState {
                        BasicUiState.Success(it)
                    }
                }
        }
    }
}