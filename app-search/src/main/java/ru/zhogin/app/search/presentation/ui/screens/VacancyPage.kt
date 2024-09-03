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
import ru.zhogin.app.search.presentation.ui.components.vacancy.CompanyBox
import ru.zhogin.app.search.presentation.ui.components.vacancy.GreenBox
import ru.zhogin.app.search.presentation.ui.components.vacancy.IconsRow
import ru.zhogin.app.search.presentation.ui.components.vacancy.QuestionBox
import ru.zhogin.app.search.presentation.ui.components.vacancy.RespondVacancy
import ru.zhogin.app.search.presentation.ui.dialog.FirstReplyDialog
import ru.zhogin.app.search.presentation.ui.dialog.SecondReplyDialog
import ru.zhogin.app.uikit.Black
import ru.zhogin.app.uikit.Grey3
import ru.zhogin.app.uikit.Text1
import ru.zhogin.app.uikit.Title1
import ru.zhogin.app.uikit.Title4

@Composable
fun VacancyPage(
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
        true -> FirstReplyDialog {
            openFirstDialog = false
        }
        else -> {}
    }

    when(openSecondDialog) {
        true -> SecondReplyDialog(text = questionText) {
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
                .padding(horizontal = 21.dp)
        ) {


            Spacer(modifier = Modifier.height(21.dp))
            IconsRow(
                vacancy = vacancy,
                onClickBack = onClickBack,
                onAddToFavourite = onAddToFavourite,
            )
            Spacer(modifier = Modifier.height(34.dp))
            Text(
                text = vacancy.title,
                style = MaterialTheme.typography.Title1
            )
            Spacer(modifier = Modifier.height(21.dp))
            Text(
                text = vacancy.salary.full,
                style = MaterialTheme.typography.Text1
            )
            Spacer(modifier = Modifier.height(21.dp))
            Text(
                text = stringResource(R.string.required_exp, vacancy.experience.text),
                style = MaterialTheme.typography.Text1
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = scheduleFromList(vacancy.schedules),
                style = MaterialTheme.typography.Text1
            )
            Spacer(modifier = Modifier.height(32.dp))
            if (vacancy.appliedNumber != null || vacancy.lookingNumber != null) {
                Row {
                    if (vacancy.appliedNumber != null) {
                        GreenBox(
                            number = vacancy.appliedNumber,
                            applied = true,
                            first = true
                        )
                    }
                    if (vacancy.appliedNumber != null && vacancy.lookingNumber != null) {
                        Spacer(modifier = Modifier.width(10.5.dp))
                        GreenBox(
                            number = vacancy.lookingNumber,
                            applied = false,
                            first = false
                        )
                    }
                    if (vacancy.lookingNumber != null && vacancy.appliedNumber == null) {
                        GreenBox(number = vacancy.lookingNumber, applied = false,
                            first = true)
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))
                CompanyBox(
                    companyName = vacancy.company,
                    companyAddress = "${vacancy.address.town}, ${vacancy.address.street}, ${vacancy.address.house}.",
                )
                Spacer(modifier = Modifier.height(21.dp))
                if (vacancy.description != null) {
                    Text(text = vacancy.description, style = MaterialTheme.typography.Text1)
                    Spacer(modifier = Modifier.height(21.dp))
                }
                Text(
                    text = stringResource(R.string.your_duties),
                    style = MaterialTheme.typography.Title1
                )
                Spacer(modifier = Modifier.height(10.5.dp))
                Text(text = vacancy.responsibilities, style = MaterialTheme.typography.Text1)
                Spacer(modifier = Modifier.height(42.dp))
                Text(
                    text = stringResource(R.string.ask_question),
                    style = MaterialTheme.typography.Title4
                )
                Spacer(modifier = Modifier.height(10.5.dp))
                Text(
                    text = stringResource(R.string.recieve_with_repl),
                    style = MaterialTheme.typography.Title4,
                    color = Grey3
                )
                Spacer(modifier = Modifier.height(21.dp))
                for (question in vacancy.questions) {
                    QuestionBox(
                        string = question,
                        onClickQuestion = {
                            questionText = it
                            openSecondDialog = true
                        }
                    )
                    Spacer(modifier = Modifier.height(10.5.dp))
                }
                Spacer(modifier = Modifier.height(10.5.dp))
                RespondVacancy(
                    toRespondOnVacancy = {
                        openFirstDialog = true
                    },
                )
            }
        }
    }
}