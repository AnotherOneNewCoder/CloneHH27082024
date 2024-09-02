package ru.zhogin.app.favourite.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.zhogin.app.search.common.RequestResult
import ru.zhogin.app.search.common.map
import ru.zhogin.app.search.common.toVacancy
import ru.zhogin.app.search.data.database.VacanciesAndOffersDatabase
import ru.zhogin.app.search.data.database.models.vacancy.VacancyDbo
import ru.zhogin.app.search.domain.models.vacancy.Vacancy
import javax.inject.Inject

class FavouriteRepository @Inject constructor(
    private val database: VacanciesAndOffersDatabase,
) {
//    fun getVacanciesFormDatabase() : Flow<RequestResult<List<Vacancy>>> {
//        val dbRequest = database.vacancyDbo::getAllVacancies.asFlow()
//            .map<List<VacancyDbo>, RequestResult<List<VacancyDbo>>> { RequestResult.Success(it) }
//        val start = flowOf<RequestResult<List<VacancyDbo>>>(RequestResult.InProgress())
//        return merge(start, dbRequest)
//            .map { result ->
//                result.map { vacancyDbos ->
//                    vacancyDbos.map { it.toVacancy() }
//                }
//            }
//    }
    fun observeAllFromDatabase() : Flow<RequestResult<List<Vacancy>>> {
        val dbRequest = database.vacancyDbo::observeAllVacanciesInFavouriteScreen
        return dbRequest.invoke().map<List<VacancyDbo>, RequestResult<List<VacancyDbo>>> {RequestResult.Success(it)}
            .map { request ->
                request.map { list ->
                    list.map { it.toVacancy() }
                }
            }
    }
    suspend fun showInfFavourite(vacancy: Vacancy) {
        if (vacancy.isFavorite) {
            database.vacancyDbo.hideInFavourite(vacancy.id)
        } else database.vacancyDbo.showInFavourite(vacancy.id)
    }

}