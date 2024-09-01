package ru.zhogin.app.search.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import ru.zhogin.app.search.common.RequestResult
import ru.zhogin.app.search.common.toRequestResult
import ru.zhogin.app.search.domain.ServerReplyApi
import ru.zhogin.app.search.domain.models.ServerReply
import javax.inject.Inject

class ServerReplyApiImpl @Inject constructor(
    private val serverReplyApi: ServerReplyApi,
) {
    fun getAllRemoteData(): Flow<RequestResult<ServerReply>> {
        val apiRequest = flow { emit(serverReplyApi.getAlRemoteData()) }
            .map { it.toRequestResult() }
        val start = flowOf<RequestResult<ServerReply>>(RequestResult.InProgress())
        return merge(apiRequest, start)
    }
    suspend fun testGetAllRemoteData() : RequestResult<ServerReply> {
        return serverReplyApi.getAlRemoteData().toRequestResult()
    }

}
