package ru.zhogin.app.search.domain.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Vacancy(
    @SerialName("address")
    val address: Address,
    @SerialName("appliedNumber")
    val appliedNumber: Int? = null,
    @SerialName("company")
    val company: String,
    @SerialName("description")
    val description: String? = null,
    @SerialName("experience")
    val experience: Experience,
    @SerialName("id")
    val id: String,
    @SerialName("isFavorite")
    val isFavorite: Boolean,
    @SerialName("lookingNumber")
    val lookingNumber: Int? = null,
    @SerialName("publishedDate")
    val publishedDate: String,
    @SerialName("questions")
    val questions: List<String>,
    @SerialName("responsibilities")
    val responsibilities: String,
    @SerialName("salary")
    val salary: Salary,
    @SerialName("schedules")
    val schedules: List<String>,
    @SerialName("title")
    val title: String
)