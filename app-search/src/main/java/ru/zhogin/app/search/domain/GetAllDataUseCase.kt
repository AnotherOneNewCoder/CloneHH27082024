package ru.zhogin.app.search.domain

import ru.zhogin.app.search.common.RequestResult
import ru.zhogin.app.search.common.toRequestResult
import ru.zhogin.app.search.data.network.models.ServerReplyDto
import javax.inject.Inject

class GetAllDataUseCase @Inject constructor(
    private val serverKtor: KtorRep
) {
    suspend operator fun invoke() : RequestResult<ServerReplyDto> = serverKtor.getAllRemoteData().toRequestResult()
}