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
import ru.zhogin.app.uikit.Text1
import ru.zhogin.app.uikit.White

@Composable
internal fun GreenBox(
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