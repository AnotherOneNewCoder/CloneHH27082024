package ru.zhogin.app.favourite.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.zhogin.app.favourite.R
import ru.zhogin.app.favourite.ui.VacancyView
import ru.zhogin.app.favourite.ui.viewmodel.FavouriteViewModel
import ru.zhogin.app.search.common.vacanciesRuEnding
import ru.zhogin.app.search.domain.models.vacancy.Vacancy
import ru.zhogin.app.uikit.Black
import ru.zhogin.app.uikit.Grey3
import ru.zhogin.app.uikit.Text1
import ru.zhogin.app.uikit.Title2

@Composable
fun FavouriteScreen(
    modifier: Modifier,
    showVacancyPage: (Vacancy) -> Unit,
) {
    FavouriteScreen(
        viewModel = hiltViewModel(),
        modifier = modifier,
        showVacancyPage = showVacancyPage,
    )
}

@Composable
internal fun FavouriteScreen(
    viewModel: FavouriteViewModel,
    modifier: Modifier,
    showVacancyPage: (Vacancy) -> Unit,
) {
    val vacanciesState by viewModel.stateVacancies.collectAsState()


    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Black)
    ) {
        LazyColumn(
            modifier = Modifier.padding(horizontal = 21.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(43.6.dp))
                Text(
                    text = stringResource(R.string.favourite_text),
                    style = MaterialTheme.typography.Title2
                )
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = stringResource(
                        R.string.amount_fav_vac,
                        vacanciesState.vacancies?.size ?: 0,
                        vacanciesRuEnding(vacanciesState.vacancies?.size ?: 0)
                    ),
                    style = MaterialTheme.typography.Text1,
                    color = Grey3
                )
                Spacer(modifier = Modifier.height(21.dp))
            }
            vacanciesState.vacancies?.size?.let {
                items(it) { count ->
                    VacancyView(
                        vacancy = vacanciesState.vacancies!![count],
                        showVacancyPage = showVacancyPage,
                        showOrHideInFavourite = { viewModel.showHideVacancy(vacanciesState.vacancies!![count]) }

                    )
                    Spacer(modifier = Modifier.height(11.dp))


                }
            }
        }
    }
}
