package ru.zhogin.app.search.data.database.models.vacancy


import androidx.room.ColumnInfo


data class AddressDbo(
    @ColumnInfo("house")
    val house: String,
    @ColumnInfo("street")
    val street: String,
    @ColumnInfo("town")
    val town: String
)