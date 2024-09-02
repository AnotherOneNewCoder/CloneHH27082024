package ru.zhogin.app.search.presentation.ui.state

import ru.zhogin.app.search.domain.models.offer.Offer

sealed class StateOffers(val offers: List<Offer>?) {
    data object None : StateOffers(offers = null)
    class Loading(offers: List<Offer>? = null) : StateOffers(offers)
    class Error(offers: List<Offer>? = null) : StateOffers(offers)
    class Success(offers: List<Offer>) : StateOffers(offers)
}