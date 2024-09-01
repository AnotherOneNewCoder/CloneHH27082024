package ru.zhogin.app.search.domain.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Address(
    @SerialName("house")
    val house: String,
    @SerialName("street")
    val street: String,
    @SerialName("town")
    val town: String
)