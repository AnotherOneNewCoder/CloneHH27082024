package ru.zhogin.app.search.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.zhogin.app.search.common.toStateOffers
import ru.zhogin.app.search.common.toStateVacancies
import ru.zhogin.app.search.domain.models.vacancy.Vacancy
import ru.zhogin.app.search.domain.usecases.GetAllOffersUseCase
import ru.zhogin.app.search.domain.usecases.GetAllVacanciesUseCase
import ru.zhogin.app.search.domain.usecases.HideOrShowVacancyUseCase
import ru.zhogin.app.search.presentation.ui.state.StateOffers
import ru.zhogin.app.search.presentation.ui.state.StateVacancies
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    offers: GetAllOffersUseCase,
    vacancies: GetAllVacanciesUseCase,
    private val showOrHide: HideOrShowVacancyUseCase,
) : ViewModel() {

    val stateOffers: StateFlow<StateOffers> = offers()
        .map { it.toStateOffers() }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), StateOffers.None)

    val stateVacancies: StateFlow<StateVacancies> = vacancies()
        .map { it.toStateVacancies() }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), StateVacancies.None)



    fun showHideVacancy(vacancy: Vacancy) {
        viewModelScope.launch {
            showOrHide(vacancy)
        }
    }


}