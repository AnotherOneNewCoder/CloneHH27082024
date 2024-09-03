package ru.zhogin.app.favourite.domain.usecases

import ru.zhogin.app.favourite.domain.repository.FavouriteRepository
import javax.inject.Inject

class GetAllVacanciesFromDataBaseUseCase @Inject constructor(
    private val repository: FavouriteRepository
) {
    operator fun invoke() = repository.observeAllFromDatabase()
}