package ru.zhogin.app.search.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url

import ru.zhogin.app.search.domain.KtorRep
import ru.zhogin.app.search.common.Resource
import ru.zhogin.app.search.data.network.models.ServerReplyDto
import javax.inject.Inject

class KtorRepImpl @Inject constructor(
    private val httpClient: HttpClient
) : KtorRep {
    override suspend fun getAllRemoteData(): Resource<ServerReplyDto> {
        return try {
            Resource.Success(
                httpClient.get {
                    url(BASE_URL)
                }.body()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Failure(error = e)
        }
    }
}

//private const val BASE_URL = "https://drive.usercontent.google.com/u/0/uc?id=1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r&export=download"
private const val BASE_URL = "https://drive.google.com/uc?export=download&id=1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r"