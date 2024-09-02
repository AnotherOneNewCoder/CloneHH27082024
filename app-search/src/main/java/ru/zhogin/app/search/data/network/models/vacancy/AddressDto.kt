package ru.zhogin.app.search.data.network.models.vacancy


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddressDto(
    @SerialName("house")
    val house: String,
    @SerialName("street")
    val street: String,
    @SerialName("town")
    val town: String
)