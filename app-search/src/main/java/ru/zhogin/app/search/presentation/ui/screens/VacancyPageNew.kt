package ru.zhogin.app.search.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.zhogin.app.search.R
import ru.zhogin.app.search.common.scheduleFromList
import ru.zhogin.app.search.domain.models.vacancy.Vacancy
import ru.zhogin.app.search.presentation.ui.components.vacancy.CompanyBoxNew
import ru.zhogin.app.search.presentation.ui.components.vacancy.GreenBoxNew
import ru.zhogin.app.search.presentation.ui.components.vacancy.IconsRowNew
import ru.zhogin.app.search.presentation.ui.components.vacancy.QuestionBoxNew
import ru.zhogin.app.search.presentation.ui.components.vacancy.RespondVacancyNew
import ru.zhogin.app.search.presentation.ui.dialog.FirstReplyDialogNew
import ru.zhogin.app.search.presentation.ui.dialog.SecondReplyDialogNew
import ru.zhogin.app.uikit.Black
import ru.zhogin.app.uikit.Grey3
import ru.zhogin.app.uikit.Text1New
import ru.zhogin.app.uikit.Title1New
import ru.zhogin.app.uikit.Title2New
import ru.zhogin.app.uikit.Title4New

@Composable
fun VacancyPageNew(
    modifier: Modifier,
    vacancy: Vacancy,
    onClickBack: () -> Unit,
    onAddToFavourite: (Vacancy) -> Unit,
) {

    var openFirstDialog by rememberSaveable {
        mutableStateOf(false)
    }

    var openSecondDialog by rememberSaveable {
        mutableStateOf(false)
    }

    var questionText by rememberSaveable {
        mutableStateOf("")
    }

    when(openFirstDialog) {
        true -> FirstReplyDialogNew {
            openFirstDialog = false
        }
        else -> {}
    }

    when(openSecondDialog) {
        true -> SecondReplyDialogNew(text = questionText) {
            openSecondDialog = false
        }
        else -> {}
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .background(Black)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {


            Spacer(modifier = Modifier.height(18.dp))
            IconsRowNew(
                vacancy = vacancy,
                onClickBack = onClickBack,
                onAddToFavourite = onAddToFavourite,
            )
            Spacer(modifier = Modifier.height(26.dp))
            Text(
                text = vacancy.title,
                style = MaterialTheme.typography.Title1New
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = vacancy.salary.full,
                style = MaterialTheme.typography.Text1New
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.required_exp, vacancy.experience.text),
                style = MaterialTheme.typography.Text1New
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = scheduleFromList(vacancy.schedules),
                style = MaterialTheme.typography.Text1New
            )
            Spacer(modifier = Modifier.height(24.dp))
            if (vacancy.appliedNumber != null || vacancy.lookingNumber != null) {
                Row {
                    if (vacancy.appliedNumber != null) {
                        GreenBoxNew(
                            number = vacancy.appliedNumber,
                            applied = true,
                            first = true
                        )
                    }
                    if (vacancy.appliedNumber != null && vacancy.lookingNumber != null) {
                        Spacer(modifier = Modifier.width(8.dp))
                        GreenBoxNew(
                            number = vacancy.lookingNumber,
                            applied = false,
                            first = false
                        )
                    }
                    if (vacancy.lookingNumber != null && vacancy.appliedNumber == null) {
                        GreenBoxNew(number = vacancy.lookingNumber, applied = false,
                            first = true)
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
                CompanyBoxNew(
                    companyName = vacancy.company,
                    companyAddress = "${vacancy.address.town}, ${vacancy.address.street}, ${vacancy.address.house}.",
                )
                Spacer(modifier = Modifier.height(16.dp))
                if (vacancy.description != null) {
                    Text(text = vacancy.description, style = MaterialTheme.typography.Text1New)
                    Spacer(modifier = Modifier.height(16.dp))
                }
                Text(
                    text = stringResource(R.string.your_duties),
                    style = MaterialTheme.typography.Title2New
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = vacancy.responsibilities, style = MaterialTheme.typography.Text1New)
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = stringResource(R.string.ask_question),
                    style = MaterialTheme.typography.Title4New
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(R.string.recieve_with_repl),
                    style = MaterialTheme.typography.Title4New,
                    color = Grey3
                )
                Spacer(modifier = Modifier.height(16.dp))
                for (question in vacancy.questions) {
                    QuestionBoxNew(
                        string = question,
                        onClickQuestion = {
                            questionText = it
                            openSecondDialog = true
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
                Spacer(modifier = Modifier.height(8.dp))
                RespondVacancyNew(
                    toRespondOnVacancy = {
                        openFirstDialog = true
                    },
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}