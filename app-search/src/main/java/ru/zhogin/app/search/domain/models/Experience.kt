package ru.zhogin.app.search.domain.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Experience(
    @SerialName("previewText")
    val previewText: String,
    @SerialName("text")
    val text: String
)