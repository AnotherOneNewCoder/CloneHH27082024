package ru.zhogin.app.search.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.zhogin.app.search.common.RequestResult
import ru.zhogin.app.search.domain.models.offer.Offer

interface OfferRepository {
    fun getOffers(): Flow<RequestResult<List<Offer>>>
}