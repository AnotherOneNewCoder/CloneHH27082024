package ru.zhogin.app.favourite.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.zhogin.app.favourite.R
import ru.zhogin.app.search.common.convertData
import ru.zhogin.app.search.common.peopleRuEnding
import ru.zhogin.app.search.domain.models.vacancy.Vacancy
import ru.zhogin.app.uikit.Blue
import ru.zhogin.app.uikit.ButtonText2
import ru.zhogin.app.uikit.Green
import ru.zhogin.app.uikit.Grey1
import ru.zhogin.app.uikit.Grey3
import ru.zhogin.app.uikit.Grey4
import ru.zhogin.app.uikit.Text1
import ru.zhogin.app.uikit.Title3
import ru.zhogin.app.uikit.White

@Composable
internal fun VacancyView(
    vacancy: Vacancy,
    showVacancyPage: (Vacancy) -> Unit,
    showOrHideInFavourite: (Vacancy) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(11.dp))
            .background(Grey1)
            .clickable(
                onClick = {
                    showVacancyPage(vacancy)
                }
            )
        ,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(17.dp)
                .background(Color.Transparent),
            contentAlignment = Alignment.TopEnd,
        ) {
            Icon(
                painter = if (vacancy.isFavorite) painterResource(id = ru.zhogin.app.search.R.drawable.icon_favourite_filled)
                else painterResource(id = ru.zhogin.app.search.R.drawable.icon_favourite_not_filled),
                contentDescription = "favourite",
                modifier = Modifier
                    .size(32.dp)
                    .clickable(
                        onClick = { showOrHideInFavourite(vacancy) }
                    )
                ,
                tint = if (vacancy.isFavorite) Blue else Grey4
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 21.dp)
        ) {
            Spacer(modifier = Modifier.height(21.dp))
            if (vacancy.lookingNumber != null) {
                Text(
                    text = stringResource(
                        R.string.now_looking_people,
                        vacancy.lookingNumber ?: "",
                        peopleRuEnding(vacancy.lookingNumber ?: 0)
                    )
                    ,
                    style = MaterialTheme.typography.Text1,
                    color = Green
                )
                Spacer(modifier = Modifier.height(13.dp))
            }
            Text(text = vacancy.title, style = MaterialTheme.typography.Title3)
            Spacer(modifier = Modifier.height(13.dp))
            Text(text = vacancy.address.town, style = MaterialTheme.typography.Text1)
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = vacancy.company, style = MaterialTheme.typography.Text1)
                Spacer(modifier = Modifier.width(11.dp))
                Icon(
                    painter = painterResource(id = ru.zhogin.app.search.R.drawable.icon_checked),
                    contentDescription = "checked",
                    modifier = Modifier
                        .size(21.dp)
                        .padding(top = 1.dp),
                    tint = Grey3
                )
            }
            Spacer(modifier = Modifier.height(14.dp))
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = ru.zhogin.app.search.R.drawable.icon_exp),
                    contentDescription = "checked",
                    modifier = Modifier
                        .size(21.dp)
                        .padding(top = 1.dp),
                    tint = White
                )
                Spacer(modifier = Modifier.width(11.dp))
                Text(text = vacancy.experience.previewText, style = MaterialTheme.typography.Text1)
            }
            Spacer(modifier = Modifier.height(13.dp))
            Text(
                text = stringResource(ru.zhogin.app.search.R.string.published_text) + convertData(vacancy.publishedDate) ,
                style = MaterialTheme.typography.Text1,
                color = Grey3
            )
            Spacer(modifier = Modifier.height(28.dp))
            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(66.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Green
                )
            ) {
                Text(
                    text = stringResource(ru.zhogin.app.search.R.string.respond_text),
                    style = MaterialTheme.typography.ButtonText2
                )
            }
            Spacer(modifier = Modifier.height(21.dp))
        }

    }

}