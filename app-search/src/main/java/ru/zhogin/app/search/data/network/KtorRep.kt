package ru.zhogin.app.search.data.network


import ru.zhogin.app.search.common.Resource
import ru.zhogin.app.search.data.network.models.ServerReplyDto

interface KtorRep {
    suspend fun getAllRemoteData() : Resource<ServerReplyDto>
}