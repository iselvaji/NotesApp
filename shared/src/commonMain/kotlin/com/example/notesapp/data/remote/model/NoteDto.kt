package com.example.notesapp.data.remote.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class NoteDto(
    @SerialName("id")
    val id: Long = 0,

    @SerialName("title")
    val title: String? = null,

    @SerialName("content")
    val content: String? = null,

    @SerialName("created")
    val created: Long = 0L
)