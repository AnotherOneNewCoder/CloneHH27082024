package ru.zhogin.app.search.presentation.ui.state

import ru.zhogin.app.search.domain.models.vacancy.Vacancy

sealed class StateVacancies(val vacancies: List<Vacancy>?) {
    data object None : StateVacancies(vacancies = null)
    class Loading(vacancies: List<Vacancy>? = null) : StateVacancies(vacancies)
    class Error(vacancies: List<Vacancy>? = null) : StateVacancies(vacancies)
    class Success(vacancies: List<Vacancy>) : StateVacancies(vacancies)
}