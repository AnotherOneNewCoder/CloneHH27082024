package ru.zhogin.app.search.data.database.models.offer

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("offers")
data class OfferDbo(
    @Embedded("-")
    val button: ButtonDbo? = null,
    @ColumnInfo("id")
    val id: String? = null,
    @ColumnInfo("link")
    val link: String,
    @PrimaryKey
    val title: String
)