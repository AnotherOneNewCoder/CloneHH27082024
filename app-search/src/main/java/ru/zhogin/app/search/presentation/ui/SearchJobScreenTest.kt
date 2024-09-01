package ru.zhogin.app.search.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.zhogin.app.search.R
import ru.zhogin.app.search.common.vacanciesRuEnding
import ru.zhogin.app.search.domain.models.ServerReply
import ru.zhogin.app.search.domain.models.Vacancy
import ru.zhogin.app.search.presentation.ui.components.OffersView
import ru.zhogin.app.search.presentation.ui.components.SearchAndFilters
import ru.zhogin.app.search.presentation.ui.components.VacanciesView
import ru.zhogin.app.search.presentation.viewmodel.SearchViewModel
import ru.zhogin.app.uikit.Black
import ru.zhogin.app.uikit.Blue
import ru.zhogin.app.uikit.Text1
import ru.zhogin.app.uikit.Title2



@Composable
fun SearchJobScreenTest(
    modifier: Modifier,
    serverReply: ServerReply,
    showVacancyPage: (Vacancy) -> Unit,
) {
    var showAll by rememberSaveable {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Black)
    ) {
        Spacer(modifier = Modifier.height(21.dp))
        SearchAndFilters(
            showAll = showAll,
            hideVacancies = {
                showAll = false
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (!showAll) {
            serverReply.offers.let {
                OffersView(listOffers = it)
                Spacer(modifier = Modifier.height(43.dp))
            }
            Text(
                text = stringResource(R.string.vacancies_for_you),
                style = MaterialTheme.typography.Title2,
                modifier = Modifier.padding(start = 21.dp)
            )
            Spacer(modifier = Modifier.height(21.dp))
            VacanciesView(listVacancies = serverReply.vacancies, showAll = showAll, onShowAllVacancy = {
                showAll = true
            }, showVacancyPage = showVacancyPage)
        } else {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 21.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "${serverReply.vacancies.size} ${
                        serverReply.vacancies.size.let {
                            vacanciesRuEnding(
                                it
                            )
                        }
                    }",
                    style = MaterialTheme.typography.Text1
                )
                Row {
                    Text(
                        text = stringResource(R.string.by_compliance),
                        style = MaterialTheme.typography.Text1,
                        color = Blue
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.icon_sort),
                        contentDescription = "sort",
                        tint = Blue,
                        modifier = Modifier.size(21.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(21.dp))
            VacanciesView(
                listVacancies = serverReply.vacancies, showAll = showAll, onShowAllVacancy = {
                    showAll = true
                },
                showVacancyPage = showVacancyPage
            )
        }
    }
}