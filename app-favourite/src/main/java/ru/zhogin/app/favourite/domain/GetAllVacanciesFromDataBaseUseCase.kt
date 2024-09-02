package ru.zhogin.app.favourite.domain

import javax.inject.Inject

class GetAllVacanciesFromDataBaseUseCase @Inject constructor(
    private val repository: FavouriteRepository
) {
    operator fun invoke() = repository.observeAllFromDatabase()
}