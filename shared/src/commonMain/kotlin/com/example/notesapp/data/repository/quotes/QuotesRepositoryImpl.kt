package com.example.notesapp.data.repository.quotes
import com.example.notesapp.data.remote.ApiService
import com.example.notesapp.data.remote.model.Quote

internal class QuotesRepositoryImpl(private val apiService: ApiService) : QuotesRepository {
    override suspend fun getQuotes(): List<Quote> {
        return apiService.getQuotes()
    }
}

