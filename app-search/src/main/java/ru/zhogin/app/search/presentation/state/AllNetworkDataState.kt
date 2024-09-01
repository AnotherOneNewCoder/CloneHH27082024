package ru.zhogin.app.search.presentation.state

import ru.zhogin.app.search.domain.models.ServerReply

data class AllNetworkDataState(
    val loading : Boolean = false,
    val error : Boolean = false,
    val serverReply: ServerReply? = null
)