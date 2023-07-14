package com.example.notesapp.data.remote

import com.example.notesapp.data.remote.model.NoteDto
import com.example.notesapp.data.remote.model.Quote
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

/**
 * Api service implementation for remote api calls with request parameters via provided http client
 *
 */

internal class ApiServiceImpl(private val client: HttpClient) : ApiService {

    override suspend fun getQuotes() : List<Quote> {
         return client.get {
             url(ApiService.EndPoints.GetQuotes.url)
         }.body()
    }

    override suspend fun getNotes(): List<NoteDto> {
        return client.get {
            url(ApiService.EndPoints.GetNotes.url)
        }.body()
    }

    override suspend fun updateNotes(notes: List<NoteDto>): List<NoteDto> {
       return client.post(ApiService.EndPoints.UpdateNotes.url) {
            contentType(ContentType.Application.Json)
            setBody(notes)
        }.body()
    }

    override suspend fun saveNote(note: NoteDto): NoteDto? {
        val response : NoteDto = client.post(ApiService.EndPoints.SaveNotes.url) {
            contentType(ContentType.Application.Json)
            setBody(note)
        }.body()
        return response
    }

    override suspend fun deleteNotes(noteId: Long) {
        client.delete("${ApiService.EndPoints.DeleteNotes.url}/$noteId")
    }
}