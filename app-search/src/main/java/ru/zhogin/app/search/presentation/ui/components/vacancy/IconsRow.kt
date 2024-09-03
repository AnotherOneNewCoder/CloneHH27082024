package ru.zhogin.app.search.presentation.ui.components.vacancy

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.zhogin.app.search.R
import ru.zhogin.app.search.domain.models.vacancy.Vacancy
import ru.zhogin.app.uikit.Blue
import ru.zhogin.app.uikit.White

@Composable
internal fun IconsRow(
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