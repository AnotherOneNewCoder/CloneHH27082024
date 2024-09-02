package ru.zhogin.app.search.data.database.models.vacancy


import androidx.room.ColumnInfo


data class SalaryDbo(
    @ColumnInfo("full")
    val full: String,
    @ColumnInfo("short")
    val short: String? = null
)