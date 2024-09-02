package ru.zhogin.app.search.data.database.models.vacancy


import androidx.room.ColumnInfo

data class ExperienceDbo(
    @ColumnInfo("preview_text")
    val previewText: String,
    @ColumnInfo("text")
    val text: String
)