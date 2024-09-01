package ru.zhogin.app.search.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.zhogin.app.search.common.RequestResult
import ru.zhogin.app.search.domain.GetAllDataUseCase
import ru.zhogin.app.search.domain.models.Offer
import ru.zhogin.app.search.domain.models.ServerReply
import ru.zhogin.app.search.presentation.state.AllNetworkDataState
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: GetAllDataUseCase
): ViewModel() {
    private val _state = MutableStateFlow<AllNetworkDataState?>(null)
    val state = _state.asStateFlow()

    private val _offers = MutableStateFlow<List<Offer>?>(null)
    val offers = _offers.asStateFlow()

    private val _stateServerReply = MutableStateFlow<RequestResult<ServerReply>?>(null)
    val stateServerReply = _stateServerReply.asStateFlow()

//    val stateServerReply: StateFlow<StateServerReply> = repository()
//        .map { it.toStateServerReply() }
//        .stateIn(viewModelScope, SharingStarted.Lazily,StateServerReply.None)

    init {
        getAllRemoteData()
    }

    fun getAllRemoteData() {
        viewModelScope.launch {
            _stateServerReply.value = repository.invoke()
        }
    }


//    private fun getAllRemoteData() {
//        viewModelScope.launch {
//
//            repository().collectLatest { result ->
//                when(result) {
//                    is RequestResult.Success -> {
//                        _state.update {
//                            _state.value?.copy(
//                                serverReply = result.data,
//                                loading = false,
//                                error = false,
//                            )
//                        }
//                    }
//
//                    is RequestResult.Error -> {
//                        _state.update {
//                            _state.value?.copy(
//                                loading = false,
//                                error = false,
//                            )
//                        }
//                    }
//                    is RequestResult.InProgress -> {
//                        _state.update {
//                            _state.value?.copy(loading = true)
//                        }
//                    }
//                }
//
//            }
//        }


//    }
}
private fun RequestResult<ServerReply>.toStateServerReply() : StateServerReply {
    return when (this) {
        is RequestResult.Error -> StateServerReply.Error(data)
        is RequestResult.InProgress -> StateServerReply.Loading(data)
        is RequestResult.Success -> StateServerReply.Success(data)
    }
}

sealed class StateServerReply(val serverReply: ServerReply?) {
    data object None : StateServerReply(serverReply = null)
    class Loading(serverReply: ServerReply? = null) : StateServerReply(serverReply)
    class Error(serverReply: ServerReply? = null) : StateServerReply(serverReply)
    class Success(serverReply: ServerReply) : StateServerReply(serverReply)
}