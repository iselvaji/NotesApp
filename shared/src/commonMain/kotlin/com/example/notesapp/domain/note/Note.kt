package com.example.notesapp.domain.note

data class Note(
    val id: Long?,
    val title: String,
    val content: String,
    val created: Long
   // val created: LocalDateTime
)