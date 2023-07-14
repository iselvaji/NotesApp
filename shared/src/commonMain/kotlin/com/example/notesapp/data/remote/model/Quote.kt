package com.example.notesapp.data.remote.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class Quote(
    @SerialName("q")
    val quote: String,

    @SerialName("a")
    val author: String,
)