package ru.zhogin.app.search.domain.usecases

import ru.zhogin.app.search.domain.repository.OfferRepository
import javax.inject.Inject

class GetAllOffersUseCase @Inject constructor(
    private val offerRepository: OfferRepository,
) {
    operator fun invoke() = offerRepository.getOffers()
}