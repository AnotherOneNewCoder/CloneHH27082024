package ru.zhogin.app.search.domain.usecases

import ru.zhogin.app.search.domain.repository.VacancyRepository
import javax.inject.Inject

class GetAllVacanciesUseCase @Inject constructor(
    private val vacancyRepository: VacancyRepository,
) {
    operator fun invoke() = vacancyRepository.getVacancies()
}
