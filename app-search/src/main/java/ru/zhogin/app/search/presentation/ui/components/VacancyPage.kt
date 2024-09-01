package ru.zhogin.app.search.presentation.ui.components

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
import ru.zhogin.app.search.common.vacanciesRuEnding
import ru.zhogin.app.search.domain.models.Vacancy
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
    onAddToFavourite: () -> Unit,
    toRespondOnVacancy: () -> Unit,
) {


    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 21.dp)
            .verticalScroll(rememberScrollState())
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
                        padding = if (vacancy.lookingNumber != null) 1 else 3
                    )
                }
                if (vacancy.appliedNumber != null && vacancy.lookingNumber != null) {
                    GreenBox(
                        number = vacancy.lookingNumber,
                        applied = false,
                        padding = 2
                    )
                }
                if (vacancy.lookingNumber != null) {
                    GreenBox(number = vacancy.lookingNumber, applied = false, padding = 3)
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
            for (question in vacancy.questions) {
                QuestionBox(string = question)
                Spacer(modifier = Modifier.height(10.5.dp))
            }
            Spacer(modifier = Modifier.height(10.5.dp))
            RespondVacancy(
                toRespondOnVacancy = toRespondOnVacancy,
            )
        }
    }
}

@Composable
private fun RespondVacancy(
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
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(67.dp))
            .background(Grey2),
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
    padding: Int,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .background(DarkGreen)
            .padding(
                start = if (padding == 2) 5.dp else 0.dp,
                end = if (padding == 1) 5.dp else 0.dp
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
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
    onAddToFavourite: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            painter = painterResource(id = R.drawable.icon_back),
            contentDescription = "back",
            modifier = Modifier
                .size(32.dp)
                .clickable(
                    onClick = onClickBack
                ),
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
                        onClick = onAddToFavourite
                    ),
                tint = if (vacancy.isFavorite) Blue else White,
            )
        }
    }
}