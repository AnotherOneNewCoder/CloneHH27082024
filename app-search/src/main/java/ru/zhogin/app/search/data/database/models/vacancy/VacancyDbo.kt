package ru.zhogin.app.search.data.database.models.vacancy


import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("vacancies")
data class VacancyDbo(
    @Embedded("-")
    val address: AddressDbo,
    @ColumnInfo("applied_number")
    val appliedNumber: Int? = null,
    @ColumnInfo("company")
    val company: String,
    @ColumnInfo("description")
    val description: String? = null,
    @Embedded("-")
    val experience: ExperienceDbo,
    @PrimaryKey
    val id: String,
    @ColumnInfo("is_favorite")
    val isFavorite: Boolean,
    @ColumnInfo("looking_number")
    val lookingNumber: Int? = null,
    @ColumnInfo("published_date")
    val publishedDate: String,
    @ColumnInfo("questions")
    val questions: String,
    @ColumnInfo("responsibilities")
    val responsibilities: String,
    @Embedded("-")
    val salary: SalaryDbo,
    @ColumnInfo("schedules")
    val schedules: String,
    @ColumnInfo("title")
    val title: String,
    @ColumnInfo("shown_favourite")
    val isShowInFavourite: Boolean,
)