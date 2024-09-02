package ru.zhogin.app.search.data.database.models.offer

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.zhogin.app.search.domain.models.Button


@Entity("offers")
data class OfferDbo(
    @ColumnInfo("button")
    val button: Button? = null,
    @ColumnInfo("id")
    val id: String? = null,
    @ColumnInfo("link")
    val link: String,
    @PrimaryKey
    val title: String
)