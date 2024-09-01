package ru.zhogin.app.search.domain.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Salary(
    @SerialName("full")
    val full: String,
    @SerialName("short")
    val short: String? = null
)