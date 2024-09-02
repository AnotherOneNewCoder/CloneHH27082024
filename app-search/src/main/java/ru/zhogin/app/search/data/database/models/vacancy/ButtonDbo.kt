package ru.zhogin.app.search.data.database.models.vacancy


import androidx.room.ColumnInfo


data class ButtonDbo(
    @ColumnInfo("text")
    val text: String
)