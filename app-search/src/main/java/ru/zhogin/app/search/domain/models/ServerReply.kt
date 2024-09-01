package ru.zhogin.app.search.domain.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ServerReply(
    @SerialName("offers")
    val offers: List<Offer>,
    @SerialName("vacancies")
    val vacancies: List<Vacancy>
)