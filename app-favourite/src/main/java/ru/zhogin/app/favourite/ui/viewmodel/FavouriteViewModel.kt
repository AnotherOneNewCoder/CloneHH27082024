package ru.zhogin.app.favourite.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.zhogin.app.favourite.domain.GetAllVacanciesFromDataBaseUseCase
import ru.zhogin.app.favourite.domain.HideOrShowVacancyUseCase
import ru.zhogin.app.search.common.toStateVacancies
import ru.zhogin.app.search.domain.models.vacancy.Vacancy
import ru.zhogin.app.search.presentation.ui.state.StateVacancies
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    vacancies: GetAllVacanciesFromDataBaseUseCase ,
    private val hideOrShowVacancyUseCase: HideOrShowVacancyUseCase
) : ViewModel() {


    val stateVacancies: StateFlow<StateVacancies> = vacancies()
        .map { it.toStateVacancies() }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), StateVacancies.None)

    fun showHideVacancy(vacancy: Vacancy) {
        viewModelScope.launch {
            hideOrShowVacancyUseCase(vacancy)
        }
    }
}