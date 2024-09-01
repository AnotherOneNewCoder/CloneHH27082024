package ru.zhogin.app.enterance.presentation.ui.components.secondscreen

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
import ru.zhogin.app.entrance.R
import ru.zhogin.app.uikit.Blue
import ru.zhogin.app.uikit.ButtonText1
import ru.zhogin.app.uikit.DarkBlue
import ru.zhogin.app.uikit.Grey4
import ru.zhogin.app.uikit.White

@Composable
internal fun ButtonConfirm(
    code: String,
    entranceDone: () -> Unit,
) {
    Card(
        shape = RoundedCornerShape(11.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (code.length <= 3) DarkBlue else Blue
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                enabled = code.length > 3,
                onClick = {
                    entranceDone()
                }
            )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(17.dp))
            Text(text = stringResource(R.string.confirm_text),
                style = MaterialTheme.typography.ButtonText1,
                color = if (code.length <= 3) Grey4 else White,
                textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(18.dp))
        }

    }
}