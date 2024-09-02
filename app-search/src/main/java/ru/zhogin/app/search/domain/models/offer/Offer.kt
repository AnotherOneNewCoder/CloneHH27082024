package ru.zhogin.app.search.domain.models.offer


data class Offer(
    val button: Button? = null,
    val id: String? = null,
    val link: String,
    val title: String
)