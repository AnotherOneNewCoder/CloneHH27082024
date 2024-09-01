package ru.zhogin.app.search.domain

import kotlinx.coroutines.flow.Flow
import ru.zhogin.app.search.common.RequestResult
import ru.zhogin.app.search.common.toRequestResult
import ru.zhogin.app.search.data.ServerReplyApiImpl
import ru.zhogin.app.search.domain.models.ServerReply
import javax.inject.Inject

class GetAllDataUseCase @Inject constructor(
    private val serverReplyApiImpl: ServerReplyApiImpl,
    private val serverKtor: KtorRep
) {
    //operator fun invoke() : Flow<RequestResult<ServerReply>> = serverReplyApiImpl.getAllRemoteData()
    //suspend operator fun invoke() : RequestResult<ServerReply> = serverReplyApiImpl.testGetAllRemoteData()
    suspend operator fun invoke() : RequestResult<ServerReply> = serverKtor.getAllRemoteData().toRequestResult()
}