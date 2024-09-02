package ru.zhogin.app.search.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.zhogin.app.search.R
import ru.zhogin.app.search.common.peopleRuEnding
import ru.zhogin.app.search.common.scheduleFromList
import ru.zhogin.app.search.domain.models.vacancy.Vacancy
import ru.zhogin.app.search.presentation.ui.dialog.FirstReplyDialog
import ru.zhogin.app.search.presentation.ui.dialog.SecondReplyDialog
import ru.zhogin.app.uikit.Black
import ru.zhogin.app.uikit.Blue
import ru.zhogin.app.uikit.ButtonText1
import ru.zhogin.app.uikit.DarkGreen
import ru.zhogin.app.uikit.Green
import ru.zhogin.app.uikit.Grey1
import ru.zhogin.app.uikit.Grey2
import ru.zhogin.app.uikit.Grey3
import ru.zhogin.app.uikit.Text1
import ru.zhogin.app.uikit.Title1
import ru.zhogin.app.uikit.Title3
import ru.zhogin.app.uikit.Title4
import ru.zhogin.app.uikit.White

@Composable
fun VacancyPage(
    modifier: Modifier,
    vacancy: Vacancy,
    onClickBack: () -> Unit,
    onAddToFavourite: (Vacancy) -> Unit,
    //toRespondOnVacancy: () -> Unit,
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

@Composable
internal fun RespondVacancy(
    toRespondOnVacancy: () -> Unit,
) {
    Card(
        shape = RoundedCornerShape(11.dp),
        colors = CardDefaults.cardColors(
            containerColor = Green
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                enabled = true,
                onClick = toRespondOnVacancy
            )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(17.dp))
            Text(
                text = stringResource(R.string.respond_text),
                style = MaterialTheme.typography.ButtonText1,
                color = White,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(18.dp))
        }
    }
}

@Composable
private fun QuestionBox(
    string: String,
    onClickQuestion: (String) -> Unit,
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(67.dp))
            .background(Grey2)
            .clickable(
                onClick = {
                    onClickQuestion(string)
                }
            )
        ,
    ) {
        Text(
            text = string,
            style = MaterialTheme.typography.Title4,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.5.dp)
        )
    }
}

@Composable
private fun CompanyBox(
    companyName: String,
    companyAddress: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(11.dp),
        colors = CardDefaults.cardColors(
            containerColor = Grey1
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 21.dp, vertical = 16.dp)
        ) {
            Row {
                Text(text = companyName, style = MaterialTheme.typography.Title3)
                Spacer(modifier = Modifier.width(11.dp))
                Icon(
                    painter = painterResource(id = R.drawable.icon_checked),
                    contentDescription = "checked",
                    modifier = Modifier
                        .size(21.dp)
                        .padding(top = 1.dp),
                    tint = Grey3
                )
            }
            Spacer(modifier = Modifier.height(11.dp))
            Image(
                painter = painterResource(id = R.drawable.company_map), contentDescription = "map",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(77.dp)
                    .clip(RoundedCornerShape(11.dp))
            )
            Spacer(modifier = Modifier.height(11.dp))
            Text(text = companyAddress, style = MaterialTheme.typography.Text1)
        }
    }

}

@Composable
private fun GreenBox(
    number: Int,
    applied: Boolean,
    first: Boolean,
) {
    Box(
        modifier = if (first) Modifier
            .fillMaxWidth(0.5f)
            .clip(RoundedCornerShape(11.dp))
            .background(DarkGreen)
        else Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(11.dp))
            .background(DarkGreen)
            
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, top = 10.dp, bottom = 10.dp, end = 14.dp)
        ) {
            Text(
                text = if (applied) stringResource(
                    R.string.people_repl,
                    number,
                    peopleRuEnding(number)
                )
                else stringResource(R.string.green_box_looking_ppl, number, peopleRuEnding(number)),
                style = MaterialTheme.typography.Text1
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
                .padding(10.dp),
            contentAlignment = Alignment.TopEnd
        ) {
            IconButton(
                onClick = { },
                modifier = Modifier.size(21.dp),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Green
                )
            ) {
                Icon(
                    painter = if (applied) painterResource(id = R.drawable.icon_profile_small)
                    else painterResource(id = R.drawable.icon_eye),
                    contentDescription = "icon",
                    tint = White
                )
            }
        }
    }
}

@Composable
private fun IconsRow(
    vacancy: Vacancy,
    onClickBack: () -> Unit,
    onAddToFavourite: (Vacancy) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            painter = painterResource(id = R.drawable.icon_back),
            contentDescription = "back",
            modifier = Modifier
                .size(19.dp)
                .clickable(
                    onClick = onClickBack
                )
                .padding(top = 6.dp)
            ,
            tint = White,
        )
        Row {
            Icon(
                painter = painterResource(id = R.drawable.icon_eye),
                contentDescription = "eye",
                modifier = Modifier.size(32.dp),
                tint = White,
            )
            Spacer(modifier = Modifier.width(21.dp))
            Icon(
                painter = painterResource(id = R.drawable.icon_share),
                contentDescription = "share",
                modifier = Modifier.size(32.dp),
                tint = White,
            )
            Spacer(modifier = Modifier.width(21.dp))
            Icon(
                painter = if (vacancy.isFavorite) painterResource(id = R.drawable.icon_favourite_filled)
                else painterResource(id = R.drawable.icon_favourite_not_filled),
                contentDescription = "liked",
                modifier = Modifier
                    .size(32.dp)
                    .clickable(
                        onClick = {onAddToFavourite(vacancy) }
                    ),
                tint = if (vacancy.isFavorite) Blue else White,
            )
        }
    }
}