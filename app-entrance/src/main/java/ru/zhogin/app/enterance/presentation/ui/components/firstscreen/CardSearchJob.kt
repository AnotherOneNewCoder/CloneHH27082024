package ru.zhogin.app.enterance.presentation.ui.components.firstscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.zhogin.app.entrance.R
import ru.zhogin.app.uikit.Grey1
import ru.zhogin.app.uikit.Red
import ru.zhogin.app.uikit.Text1
import ru.zhogin.app.uikit.Title3

@Composable
internal fun CardSearchJob(
    email: String,
    changeEmail: (String) -> Unit,
    moveToSecondEntrance: (String) -> Unit,
    error: Boolean,
) {
    val focusManager = LocalFocusManager.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 21.dp),
        shape = RoundedCornerShape(11.dp),
        colors = CardDefaults.cardColors(
            containerColor = Grey1
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 21.dp)
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = stringResource(id = R.string.find_job),
                style = MaterialTheme.typography.Title3)
            Spacer(modifier = Modifier.height(21.dp))
            EmailInputField(email = email, changeEmail = changeEmail,
                clearFocus = { focusManager.clearFocus() },
                error = error
            )
            if (error) {
                Spacer(modifier = Modifier.height(21.dp))
                Text(text = stringResource(R.string.entered_wrong_e_mail),
                    style = MaterialTheme.typography.Text1,
                    color = Red)
            }
            Spacer(modifier = Modifier.height(21.dp))
            EmailEnterButtons(
                email = email,
                moveToSecondEntrance = {
                    moveToSecondEntrance(it)
                })
            Spacer(modifier = Modifier.height(32.dp))
        }

    }
}