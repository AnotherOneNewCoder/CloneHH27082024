package ru.zhogin.app.search.presentation.ui.components.vacancy

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.zhogin.app.search.R
import ru.zhogin.app.search.domain.models.vacancy.Vacancy
import ru.zhogin.app.uikit.Blue
import ru.zhogin.app.uikit.White

@Composable
internal fun IconsRowNew(
    vacancy: Vacancy,
    onClickBack: () -> Unit,
    onAddToFavourite: (Vacancy) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth()
            .height(28.dp)
        ,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.icon_back),
            contentDescription = "back",
            modifier = Modifier
                .size(14.dp)
                .clickable(
                    onClick = onClickBack
                )
            ,
            tint = White,
        )
        Row {
            Icon(
                painter = painterResource(id = R.drawable.icon_eye),
                contentDescription = "eye",
                modifier = Modifier.size(27.dp),
                tint = White,
            )
            Spacer(modifier = Modifier.width(16.dp))
            Icon(
                painter = painterResource(id = R.drawable.icon_share),
                contentDescription = "share",
                modifier = Modifier.size(24.dp),
                tint = White,
            )
            Spacer(modifier = Modifier.width(16.dp))
            Icon(
                painter = if (vacancy.isFavorite) painterResource(id = R.drawable.icon_favourite_filled)
                else painterResource(id = R.drawable.icon_favourite_not_filled),
                contentDescription = "liked",
                modifier = Modifier
                    .size(24.dp)
                    .clickable(
                        onClick = {onAddToFavourite(vacancy) }
                    ),
                tint = if (vacancy.isFavorite) Blue else White,
            )
        }
    }
}