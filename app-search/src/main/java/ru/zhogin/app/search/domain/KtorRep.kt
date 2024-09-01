package ru.zhogin.app.search.domain

import ru.zhogin.app.search.domain.models.ServerReply

interface KtorRep {
    suspend fun getAllRemoteData() : Resource<ServerReply>
}