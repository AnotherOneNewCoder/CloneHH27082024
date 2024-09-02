package ru.zhogin.app.search.domain.usecases

import ru.zhogin.app.search.domain.models.vacancy.Vacancy
import ru.zhogin.app.search.domain.repository.VacancyRepository
import javax.inject.Inject

class HideOrShowVacancyUseCase @Inject constructor(
    private val vacancyRepository: VacancyRepository,
) {
    suspend operator fun invoke(vacancy: Vacancy) = vacancyRepository.showInfFavourite(vacancy)
}
