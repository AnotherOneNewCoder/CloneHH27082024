package ru.zhogin.app.search.domain.models.vacancy


data class Vacancy(
    val address: Address,
    val appliedNumber: Int? = null,
    val company: String,
    val description: String? = null,
    val experience: Experience,
    val id: String,
    val isFavorite: Boolean,
    val lookingNumber: Int? = null,
    val publishedDate: String,
    val questions: List<String>,
    val responsibilities: String,
    val salary: Salary,
    val schedules: List<String>,
    val title: String,
)