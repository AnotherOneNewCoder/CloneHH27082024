package ru.zhogin.app.search.data.network.models.offer


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ButtonDto(
    @SerialName("text")
    val text: String
)