package com.example.notesapp.data.local.note

import com.example.notesapp.data.remote.model.NoteDto
import com.example.notesapp.domain.note.Note
import database.NoteEntity

fun NoteEntity.toNote(): Note {
    return Note(
        id = id,
        title = title,
        content = content,
        created = created
    )
}


fun NoteDto.toNote(): Note {
    return Note(
        id = id,
        title = title ?: "",
        content = content ?: "",
        created = created ?: 0L
    )
}

fun Note.toNoteDto(): NoteDto {
    return NoteDto(
        id = id ?: 0L,
        title = title ?: "",
        content = content ?: "",
        created = created ?: 0L
    )
}