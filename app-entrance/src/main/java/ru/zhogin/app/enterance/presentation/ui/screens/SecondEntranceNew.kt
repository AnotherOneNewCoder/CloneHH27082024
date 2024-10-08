package ru.zhogin.app.enterance.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import ru.zhogin.app.enterance.presentation.ui.components.secondscreen.ButtonConfirmNew
import ru.zhogin.app.enterance.presentation.ui.components.secondscreen.OptZoneNew
import ru.zhogin.app.entrance.R
import ru.zhogin.app.uikit.Black
import ru.zhogin.app.uikit.Title2New
import ru.zhogin.app.uikit.Title3New
import ru.zhogin.app.uikit.White


private val textList = listOf(
    mutableStateOf(
        TextFieldValue(
            text = "",
            selection = TextRange(0)
        )
    ),
    mutableStateOf(
        TextFieldValue(
            text = "",
            selection = TextRange(0)
        )
    ),
    mutableStateOf(
        TextFieldValue(
            text = "",
            selection = TextRange(0)
        )
    ),
    mutableStateOf(
        TextFieldValue(
            text = "",
            selection = TextRange(0)
        )
    ),
)
private val requesterList = listOf(
    FocusRequester(),
    FocusRequester(),
    FocusRequester(),
    FocusRequester(),
)


@Composable
internal fun SecondEntranceNew(
    modifier: Modifier,
    email: String,
    entranceDone: () -> Unit,
) {
    var code by rememberSaveable {
        mutableStateOf("")
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Black),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(130.dp))
            Text(
                text = stringResource(R.string.send_code_text, email),
                style = MaterialTheme.typography.Title2New,
                color = White
            )
            Spacer(modifier = Modifier.height(21.dp))
            Text(
                text = stringResource(R.string.write_code_long_text),
                style = MaterialTheme.typography.Title3New,
            )
            Spacer(modifier = Modifier.height(16.dp))
            OptZoneNew(textList = textList, requesterList = requesterList, verifyCode = {
                code = it
            })
            Spacer(modifier = Modifier.height(16.dp))
            ButtonConfirmNew(
                code = code,
                entranceDone = entranceDone
            )
        }

    }
}