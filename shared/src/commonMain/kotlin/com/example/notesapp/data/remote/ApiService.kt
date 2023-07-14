package com.example.notesapp.data.remote

import com.example.notesapp.data.remote.model.NoteDto
import com.example.notesapp.data.remote.model.Quote

/**
 * Api service interface which contains the remote api function and end points
 */

internal interface ApiService {

    companion object {
        const val BASE_URL = "https://zenquotes.io/api/quotes"
        const val NOTES_BASE_URL = "http://192.168.1.9:8080"
        const val END_POINT_GET_NOTES = "/notes"
        const val END_POINT_SAVE_NOTES = "/saveNotes"
        const val END_POINT_UPDATE_NOTES = "/updateNotes"
    }

    sealed class EndPoints(val url: String) {
        object GetQuotes : EndPoints(BASE_URL)
        object GetNotes : EndPoints(NOTES_BASE_URL + END_POINT_GET_NOTES)
        object SaveNotes : EndPoints(NOTES_BASE_URL + END_POINT_SAVE_NOTES)
        object DeleteNotes : EndPoints(NOTES_BASE_URL)
        object UpdateNotes : EndPoints(NOTES_BASE_URL + END_POINT_UPDATE_NOTES)
    }

    suspend fun getQuotes() : List<Quote>

    suspend fun getNotes() : List<NoteDto>
    suspend fun saveNote(note: NoteDto) : NoteDto?
    suspend fun deleteNotes(noteId: Long)
    suspend fun updateNotes(note: List<NoteDto>) : List<NoteDto>
}