package ru.zhogin.app.search.domain.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Offer(
    @SerialName("button")
    val button: Button? = null,
    @SerialName("id")
    val id: String? = null,
    @SerialName("link")
    val link: String,
    @SerialName("title")
    val title: String
)