package com.example.notesapp.domain.usecase

import com.example.notesapp.data.remote.model.Quote
import com.example.notesapp.data.repository.quotes.QuotesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class QuotesUseCase(private val repository: QuotesRepository) {
    suspend fun getQuotes(): Flow<List<Quote>> = flow {
        emit(repository.getQuotes())
    }
}