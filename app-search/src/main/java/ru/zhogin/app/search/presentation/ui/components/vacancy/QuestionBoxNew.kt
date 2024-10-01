package ru.zhogin.app.search.presentation.ui.components.vacancy

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import ru.zhogin.app.uikit.Grey2
import ru.zhogin.app.uikit.Title4New

@Composable
internal fun QuestionBoxNew(
    modifier: Modifier = Modifier,
    string: String,
    onClickQuestion: (String) -> Unit,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(50.dp))
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
            style = MaterialTheme.typography.Title4New,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
        )
    }
}