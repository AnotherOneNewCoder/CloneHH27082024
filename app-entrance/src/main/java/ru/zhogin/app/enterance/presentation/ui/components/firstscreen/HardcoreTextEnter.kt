package ru.zhogin.app.enterance.presentation.ui.components.firstscreen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.zhogin.app.uikit.Title2

@Composable
internal fun HardcoreTextEnter(
    text: String
) {
    Row {
        Spacer(modifier = Modifier.width(21.dp))
        Text(text = text,
            style = MaterialTheme.typography.Title2)
    }

}