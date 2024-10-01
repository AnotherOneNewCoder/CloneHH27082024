package ru.zhogin.app.enterance.presentation.ui.components.firstscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
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
import ru.zhogin.app.uikit.ButtonText2New
import ru.zhogin.app.uikit.DarkBlue
import ru.zhogin.app.uikit.Grey4
import ru.zhogin.app.uikit.White

@Composable
internal fun EmailEnterButtonsNew(
    email: String,
    moveToSecondEntrance: (String) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = if (email.isEmpty()) DarkBlue else Blue
            ),
            modifier = Modifier
                .weight(1f)
                .clickable(
                    enabled = email.isNotEmpty(),
                    onClick = {
                        moveToSecondEntrance(email)
                    }
                )
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(11.dp))
                Text(text = stringResource(R.string.continue_text),
                    style = MaterialTheme.typography.ButtonText2New,
                    color = if (email.isEmpty()) Grey4 else White,
                    textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.height(11.dp))
            }

        }
        Spacer(modifier = Modifier.width(24.dp))
        Text(text = stringResource(R.string.enter_with_pass),
            style = MaterialTheme.typography.ButtonText2New,
            color = Blue)
    }
}