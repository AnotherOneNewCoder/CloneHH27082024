package ru.zhogin.app.search.data.database.models.offer


import androidx.room.ColumnInfo


data class ButtonDbo(
    @ColumnInfo("text")
    val text: String
)