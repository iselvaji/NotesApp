package com.example.notesapp.data.repository.notes

import com.example.notesapp.data.local.note.toNote
import com.example.notesapp.data.local.note.toNoteDto
import com.example.notesapp.data.remote.ApiService
import com.example.notesapp.data.remote.model.NoteDto
import com.example.notesapp.domain.note.Note
import com.example.notesapp.domain.note.NoteDataSource

internal class NotesRepositoryImpl(private val noteDataSource: NoteDataSource,
                                   private val apiService: ApiService) : NotesRepository {

    override suspend fun getNotes(): List<Note> {
       return try {
           val data = apiService.getNotes().map { it.toNote() }
           saveDataLocally(data)
           data
        } catch (ex : Exception) {
            noteDataSource.getAllNotes()
        }
    }

    override suspend fun deleteNoteById(id: Long) {
        try {
           apiService.deleteNotes(id)
        } catch (ex: Exception) {
            noteDataSource.deleteNoteById(id)
        }
    }

    override suspend fun syncNotes() : List<Note> {
        return try {
            val localNotes = noteDataSource.getAllNotes()
            val notesDto = arrayListOf<NoteDto>()
            localNotes.forEach {
                notesDto.add(it.toNoteDto())
            }
            apiService.updateNotes(notesDto).map { it.toNote() }
        } catch (ex: Exception) {
            noteDataSource.getAllNotes()
        }
    }

    override suspend fun saveNotes(note: Note) {
        try {
            apiService.saveNote(note.toNoteDto())
        } catch (ex: Exception) {
            noteDataSource.insertNote(note)
        }
    }

    private suspend fun saveDataLocally(data : List<Note>) {
        noteDataSource.deleteAll()
        data.forEach {
            noteDataSource.insertNote(it)
        }
    }
}

