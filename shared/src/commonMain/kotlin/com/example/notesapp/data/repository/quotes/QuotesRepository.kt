package com.example.notesapp.data.repository.quotes

import com.example.notesapp.data.remote.model.Quote

interface QuotesRepository {
    suspend fun getQuotes() : List<Quote>
}