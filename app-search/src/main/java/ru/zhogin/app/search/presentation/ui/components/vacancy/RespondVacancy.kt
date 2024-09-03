package ru.zhogin.app.search.presentation.ui.components.vacancy

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.zhogin.app.search.R
import ru.zhogin.app.uikit.ButtonText1
import ru.zhogin.app.uikit.Green
import ru.zhogin.app.uikit.White

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