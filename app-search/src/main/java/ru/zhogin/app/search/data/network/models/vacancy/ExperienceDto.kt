package ru.zhogin.app.search.data.network.models.vacancy


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExperienceDto(
    @SerialName("previewText")
    val previewText: String,
    @SerialName("text")
    val text: String
)