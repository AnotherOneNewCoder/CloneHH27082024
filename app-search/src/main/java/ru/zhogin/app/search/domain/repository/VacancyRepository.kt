package ru.zhogin.app.search.domain.repository

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import ru.zhogin.app.search.common.DefaultRequestResponseMereStrategy
import ru.zhogin.app.search.common.MergeStrategy
import ru.zhogin.app.search.common.RequestResult
import ru.zhogin.app.search.common.Resource
import ru.zhogin.app.search.common.map
import ru.zhogin.app.search.common.toRequestResult
import ru.zhogin.app.search.common.toVacancy
import ru.zhogin.app.search.common.toVacancyDbo
import ru.zhogin.app.search.data.database.VacanciesAndOffersDatabase
import ru.zhogin.app.search.data.database.models.vacancy.VacancyDbo
import ru.zhogin.app.search.data.network.models.vacancy.VacancyDto
import ru.zhogin.app.search.domain.KtorRep
import ru.zhogin.app.search.domain.models.vacancy.Vacancy
import javax.inject.Inject

class VacancyRepository @Inject constructor(
    private val database: VacanciesAndOffersDatabase,
    private val api: KtorRep
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    fun getVacancies(
        mergeStrategy: MergeStrategy<RequestResult<List<Vacancy>>> = DefaultRequestResponseMereStrategy()
    ) : Flow<RequestResult<List<Vacancy>>> {
        val cache = getAllVacanciesFromServer()
        val remote = getVacanciesFormDatabase()

        return cache.combine(remote, mergeStrategy::merge)
            .flatMapLatest { result ->
                if (result is RequestResult.Success) {
                    database.vacancyDbo.observeAllVacancies()
                        .map { dbos ->
                            dbos.map { it.toVacancy() }
                        }
                        .map { RequestResult.Success(it) }
                } else {
                    flowOf(result)
                }
            }
    }

    suspend fun showInfFavourite(vacancy: Vacancy) {
        if (vacancy.isFavorite) {
            database.vacancyDbo.hideInFavourite(vacancy.id)
        } else database.vacancyDbo.showInFavourite(vacancy.id)
    }
    private fun getAllVacanciesFromServer() : Flow<RequestResult<List<Vacancy>>> {
        val apiOffer : Flow<RequestResult<List<Vacancy>>> = flow { emit(api.getAllRemoteData()) }
            .onEach { resource ->
                if (resource is Resource.Success) {
                    saveNetOffersToCache(resource.data.vacancies)
                }
            }
            .map { it.toRequestResult() }
            .map { it.map { serverReplyDto ->
                serverReplyDto.vacancies.map { vacanciesDto ->
                    vacanciesDto.toVacancy()
                }
            } }
        val start = flowOf<RequestResult<List<Vacancy>>>(RequestResult.InProgress())
        return merge(apiOffer, start)
    }

    private suspend fun saveNetOffersToCache(vacanciesDto: List<VacancyDto>) {
        val dbos = vacanciesDto.map { it.toVacancyDbo() }
        database.vacancyDbo.insertVacancies(dbos)
    }
    private fun getVacanciesFormDatabase() : Flow<RequestResult<List<Vacancy>>> {
        val dbRequest = database.vacancyDbo::getAllVacancies.asFlow()
            .map<List<VacancyDbo>, RequestResult<List<VacancyDbo>>> { RequestResult.Success(it) }
        val start = flowOf<RequestResult<List<VacancyDbo>>>(RequestResult.InProgress())
        return merge(start, dbRequest)
            .map { result ->
                result.map { vacancyDbos ->
                    vacancyDbos.map { it.toVacancy() }
                }
            }
    }
}