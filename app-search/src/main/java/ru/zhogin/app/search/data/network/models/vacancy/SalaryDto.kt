package ru.zhogin.app.search.data.network.models.vacancy


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SalaryDto(
    @SerialName("full")
    val full: String,
    @SerialName("short")
    val short: String? = null
)