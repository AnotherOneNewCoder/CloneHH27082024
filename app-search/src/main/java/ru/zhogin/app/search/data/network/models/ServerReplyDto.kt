package ru.zhogin.app.search.data.network.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.zhogin.app.search.data.network.models.offer.OfferDto
import ru.zhogin.app.search.data.network.models.vacancy.VacancyDto

@Serializable
data class ServerReplyDto(
    @SerialName("offers")
    val offers: List<OfferDto>,
    @SerialName("vacancies")
    val vacancies: List<VacancyDto>
)