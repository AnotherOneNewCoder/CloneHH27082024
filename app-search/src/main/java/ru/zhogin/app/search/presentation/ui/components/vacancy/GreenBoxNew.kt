package ru.zhogin.app.search.presentation.ui.components.vacancy

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
import ru.zhogin.app.search.R
import ru.zhogin.app.search.common.peopleRuEnding
import ru.zhogin.app.uikit.DarkGreen
import ru.zhogin.app.uikit.Green
import ru.zhogin.app.uikit.Text1New
import ru.zhogin.app.uikit.White

@Composable
internal fun GreenBoxNew(
    number: Int,
    applied: Boolean,
    first: Boolean,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = if (first) modifier
            .fillMaxWidth(0.5f)
            .clip(RoundedCornerShape(8.dp))
            .background(DarkGreen)
        else Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(DarkGreen)

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, top = 6.dp, bottom = 6.dp, end = 45.dp)
        ) {
            Text(
                text = if (applied) stringResource(
                    R.string.people_repl,
                    number,
                    peopleRuEnding(number)
                )
                else stringResource(R.string.green_box_looking_ppl, number, peopleRuEnding(number)),
                style = MaterialTheme.typography.Text1New
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
                .padding(8.dp),
            contentAlignment = Alignment.TopEnd
        ) {
            IconButton(
                onClick = { },
                modifier = Modifier.size(16.dp),
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