package ru.zhogin.app.favourite.domain.usecases

import ru.zhogin.app.favourite.domain.repository.FavouriteRepository
import ru.zhogin.app.search.domain.models.vacancy.Vacancy
import javax.inject.Inject

class HideOrShowVacancyUseCase @Inject constructor(
    private val repository: FavouriteRepository,
) {
    suspend operator fun invoke(vacancy: Vacancy) = repository.showInfFavourite(vacancy)
}
