package ru.zhogin.app.search.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.zhogin.app.search.common.RequestResult
import ru.zhogin.app.search.domain.models.vacancy.Vacancy

interface VacancyRepository {
    fun getVacancies() : Flow<RequestResult<List<Vacancy>>>
    suspend fun showInfFavourite(vacancy: Vacancy)
}