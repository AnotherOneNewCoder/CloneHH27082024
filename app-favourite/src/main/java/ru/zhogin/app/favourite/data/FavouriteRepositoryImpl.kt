package ru.zhogin.app.favourite.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.zhogin.app.favourite.domain.repository.FavouriteRepository
import ru.zhogin.app.search.common.RequestResult
import ru.zhogin.app.search.common.map
import ru.zhogin.app.search.common.toVacancy
import ru.zhogin.app.search.data.database.VacanciesAndOffersDatabase
import ru.zhogin.app.search.data.database.models.vacancy.VacancyDbo
import ru.zhogin.app.search.domain.models.vacancy.Vacancy
import javax.inject.Inject

class FavouriteRepositoryImpl @Inject constructor(
    private val database: VacanciesAndOffersDatabase,
) : FavouriteRepository{
    override fun observeAllFromDatabase() : Flow<RequestResult<List<Vacancy>>> {
        val dbRequest = database.vacancyDbo::observeAllVacanciesInFavouriteScreen
        return dbRequest.invoke().map<List<VacancyDbo>, RequestResult<List<VacancyDbo>>> {RequestResult.Success(it)}
            .map { request ->
                request.map { list ->
                    list.map { it.toVacancy() }
                }
            }
    }
    override suspend fun showInfFavourite(vacancy: Vacancy) {
        if (vacancy.isFavorite) {
            database.vacancyDbo.hideInFavourite(vacancy.id)
        } else database.vacancyDbo.showInFavourite(vacancy.id)
    }

}