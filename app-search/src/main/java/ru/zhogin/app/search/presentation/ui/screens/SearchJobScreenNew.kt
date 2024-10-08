package ru.zhogin.app.search.presentation.ui.screens

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.zhogin.app.search.R
import ru.zhogin.app.search.common.vacanciesRuEnding
import ru.zhogin.app.search.domain.models.offer.Offer
import ru.zhogin.app.search.domain.models.vacancy.Vacancy
import ru.zhogin.app.search.presentation.ui.components.search.OffersViewNew
import ru.zhogin.app.search.presentation.ui.components.search.SearchAndFiltersNew
import ru.zhogin.app.search.presentation.ui.components.search.VacanciesViewNew
import ru.zhogin.app.uikit.Black
import ru.zhogin.app.uikit.Blue
import ru.zhogin.app.uikit.Text1New
import ru.zhogin.app.uikit.Title2New

@Composable
fun SearchJobScreenNew(
    modifier: Modifier,
    showVacancyPage: (Vacancy) -> Unit,
    listOffers: List<Offer>?,
    listVacancies: List<Vacancy>,
    showOrHideInFavourite: (Vacancy) -> Unit,
) {
    var showAll by rememberSaveable {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Black)
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        SearchAndFiltersNew(
            showAll = showAll,
            hideVacancies = {
                showAll = false
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (!showAll) {
            listOffers?.let {
                OffersViewNew(listOffers = it)
                Spacer(modifier = Modifier.height(32.dp))
            }
            Text(
                text = stringResource(R.string.vacancies_for_you),
                style = MaterialTheme.typography.Title2New,
                modifier = Modifier.padding(start = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            VacanciesViewNew(
                listVacancies = listVacancies,
                showAll = showAll,
                onShowAllVacancy = {
                    showAll = true
                },
                showVacancyPage = showVacancyPage,
                showOrHideInFavourite = showOrHideInFavourite
            )
        } else {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "${listVacancies.size} ${
                        vacanciesRuEnding(
                            listVacancies.size
                        )
                    }",
                    style = MaterialTheme.typography.Text1New
                )
                Row {
                    Text(
                        text = stringResource(R.string.by_compliance),
                        style = MaterialTheme.typography.Text1New,
                        color = Blue
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.icon_sort),
                        contentDescription = "sort",
                        tint = Blue,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(25.dp))
            VacanciesViewNew(
                listVacancies = listVacancies, showAll = showAll, onShowAllVacancy = {
                    showAll = true
                },
                showVacancyPage = showVacancyPage,
                showOrHideInFavourite = showOrHideInFavourite
            )
        }
    }
}