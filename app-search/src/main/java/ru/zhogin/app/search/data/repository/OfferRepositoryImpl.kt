package ru.zhogin.app.search.data.repository

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
import ru.zhogin.app.search.common.toOffer
import ru.zhogin.app.search.common.toOfferDbo
import ru.zhogin.app.search.common.toRequestResult
import ru.zhogin.app.search.data.database.VacanciesAndOffersDatabase
import ru.zhogin.app.search.data.database.models.offer.OfferDbo
import ru.zhogin.app.search.data.network.KtorRep
import ru.zhogin.app.search.data.network.models.offer.OfferDto
import ru.zhogin.app.search.domain.models.offer.Offer
import ru.zhogin.app.search.domain.repository.OfferRepository
import javax.inject.Inject

class OfferRepositoryImpl @Inject constructor(
    private val database: VacanciesAndOffersDatabase,
    private val api: KtorRep
): OfferRepository {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getOffers(
    ) : Flow<RequestResult<List<Offer>>> {
        val mergeStrategy: MergeStrategy<RequestResult<List<Offer>>> = DefaultRequestResponseMereStrategy()
        val cache = getOffersFormDatabase()
        val remote = getAllOffersFromServer()

        return cache.combine(remote, mergeStrategy::merge)
            .flatMapLatest { result ->
                if (result is RequestResult.Success) {
                    database.offerDao.observeAllOffers()
                        .map { dbos ->
                            dbos.map { it.toOffer() }
                        }
                        .map { RequestResult.Success(it) }
                } else {
                    flowOf(result)
                }
            }
    }



    private fun getAllOffersFromServer() : Flow<RequestResult<List<Offer>>> {
        val apiOffer : Flow<RequestResult<List<Offer>>> = flow { emit(api.getAllRemoteData()) }
            .onEach { resource ->
                if (resource is Resource.Success) {
                    saveNetOffersToCache(resource.data.offers)
                }
            }
            .map { it.toRequestResult() }
            .map { it.map { serverReplyDto ->
                serverReplyDto.offers.map { offerDto ->
                    offerDto.toOffer()
                }
            } }
        val start = flowOf<RequestResult<List<Offer>>>(RequestResult.InProgress())
        return merge(apiOffer, start)
    }

    private suspend fun saveNetOffersToCache(offersDto: List<OfferDto>) {
        val dbos = offersDto.map { it.toOfferDbo() }
        database.offerDao.insertOffers(dbos)
    }

    private fun getOffersFormDatabase() : Flow<RequestResult<List<Offer>>> {
        val dbRequest = database.offerDao::getAllOffers.asFlow()
            .map<List<OfferDbo>, RequestResult<List<OfferDbo>>> { RequestResult.Success(it) }
        val start = flowOf<RequestResult<List<OfferDbo>>>(RequestResult.InProgress())
        return merge(start, dbRequest)
            .map { result ->
                result.map { offerDbos ->
                    offerDbos.map { it.toOffer() }
                }
            }
    }

}