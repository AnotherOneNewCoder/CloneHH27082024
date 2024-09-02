package ru.zhogin.app.search.common

import ru.zhogin.app.search.domain.models.offer.Offer
import ru.zhogin.app.search.domain.models.vacancy.Vacancy
import ru.zhogin.app.search.presentation.ui.state.StateOffers
import ru.zhogin.app.search.presentation.ui.state.StateVacancies

sealed class RequestResult<out E : Any>(open val data: E? = null) {

    class InProgress<E : Any>(data: E? = null) : RequestResult<E>(data)
    class Success<E : Any>(override val data: E) : RequestResult<E>(data)
    class Error<E : Any>(data: E? = null, val error: Throwable? = null) : RequestResult<E>(data)
}

fun <I : Any, O : Any> RequestResult<I>.map(mapper: (I) -> O): RequestResult<O> {
    return when (this) {
        is RequestResult.Error -> RequestResult.Error(data?.let(mapper))
        is RequestResult.InProgress -> RequestResult.InProgress(data?.let(mapper))
        is RequestResult.Success -> RequestResult.Success(mapper(data))
    }
}


internal fun <T : Any> Resource<T>.toRequestResult() : RequestResult<T> {
    return when(this) {
        is Resource.Failure -> RequestResult.Error(data)
        is Resource.Loading -> RequestResult.InProgress(data)
        is Resource.Success -> RequestResult.Success(data)
    }
}

internal fun RequestResult<List<Offer>>.toStateOffers() : StateOffers {
    return when(this) {
        is RequestResult.Error -> StateOffers.Error(data)
        is RequestResult.InProgress -> StateOffers.Loading(data)
        is RequestResult.Success -> StateOffers.Success(data)
    }
}

fun RequestResult<List<Vacancy>>.toStateVacancies() : StateVacancies {
    return when(this) {
        is RequestResult.Error -> StateVacancies.Error(data)
        is RequestResult.InProgress -> StateVacancies.Loading(data)
        is RequestResult.Success -> StateVacancies.Success(data)
    }
}
