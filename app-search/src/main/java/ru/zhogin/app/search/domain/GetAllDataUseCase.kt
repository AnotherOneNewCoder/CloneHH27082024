package ru.zhogin.app.search.domain

import ru.zhogin.app.search.common.RequestResult
import ru.zhogin.app.search.common.toRequestResult
import ru.zhogin.app.search.domain.models.ServerReply
import javax.inject.Inject

class GetAllDataUseCase @Inject constructor(
    private val serverKtor: KtorRep
) {
    suspend operator fun invoke() : RequestResult<ServerReply> = serverKtor.getAllRemoteData().toRequestResult()
}