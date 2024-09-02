package ru.zhogin.app.search.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.zhogin.app.search.common.RequestResult
import ru.zhogin.app.search.domain.GetAllDataUseCase
import ru.zhogin.app.search.domain.models.Address
import ru.zhogin.app.search.domain.models.Experience
import ru.zhogin.app.search.domain.models.Salary
import ru.zhogin.app.search.domain.models.ServerReply
import ru.zhogin.app.search.domain.models.Vacancy
import javax.inject.Inject

private val emptyVacancy = Vacancy(
    address = Address(
        house = "", street = "", town = ""
    ),
    company = "",
    experience = Experience(
        previewText = "", text = ""
    ),
    title = "",
    schedules = listOf("", ""),
    salary = Salary(full = ""),
    responsibilities = "",
    questions = listOf("", ""),
    publishedDate = "",
    isFavorite = true,
    id = ""
)

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: GetAllDataUseCase
): ViewModel() {

    private val _stateServerReply = MutableStateFlow<RequestResult<ServerReply>?>(null)
    val stateServerReply = _stateServerReply.asStateFlow()

    private val _cachedVacancy = MutableStateFlow(emptyVacancy)
    val cachedVacancy = _cachedVacancy.asStateFlow()


    init {
        getAllRemoteData()
    }

    private fun getAllRemoteData() {
        viewModelScope.launch {
            _stateServerReply.value = repository.invoke()
        }
    }
    fun saveVacancyInCache(vacancy: Vacancy) {
        viewModelScope.launch {
            _cachedVacancy.value = vacancy
        }
    }

    fun clearVacancyCache() {
        _cachedVacancy.value = emptyVacancy
    }



}
