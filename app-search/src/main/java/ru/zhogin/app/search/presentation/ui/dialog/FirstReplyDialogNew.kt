package ru.zhogin.app.search.presentation.ui.dialog

import android.view.Gravity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.DialogWindowProvider
import ru.zhogin.app.search.R
import ru.zhogin.app.search.presentation.ui.components.vacancy.RespondVacancyNew
import ru.zhogin.app.uikit.Black
import ru.zhogin.app.uikit.ButtonText1New
import ru.zhogin.app.uikit.Green
import ru.zhogin.app.uikit.Grey2
import ru.zhogin.app.uikit.Grey3
import ru.zhogin.app.uikit.Text1New
import ru.zhogin.app.uikit.Title3New
import ru.zhogin.app.uikit.White

@Composable
internal fun FirstReplyDialogNew(
    onDismissRequest: () -> Unit,
) {
    var openTextField by rememberSaveable {
        mutableStateOf(false)
    }
    var text by rememberSaveable {
        mutableStateOf("")
    }
    Dialog(
        onDismissRequest = {
            openTextField = false
            onDismissRequest()
        },
        properties = DialogProperties(
            usePlatformDefaultWidth = false // experimental
        )
    ) {
        EditDialogWindow()
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Black
            ),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider(
                    color = Grey2,
                    thickness = 5.dp,
                    modifier = Modifier
                        .width(38.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier.padding(start = 16.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ellipse_avatar),
                        contentDescription = "avatar",
                        modifier = Modifier.size(48.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = stringResource(R.string.hardcore_cv_add),
                            style = MaterialTheme.typography.Text1New,
                            color = Grey3,
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = stringResource(R.string.harcore_job_position),
                            style = MaterialTheme.typography.Title3New,
                            color = White,
                        )
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
                HorizontalDivider(
                    color = Grey2,
                    thickness = 1.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
                if (!openTextField) {
                    Spacer(modifier = Modifier.height(40.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(R.string.add_accompanying),
                            style = MaterialTheme.typography.ButtonText1New,
                            color = Green,
                            modifier = Modifier.clickable(
                                onClick = {
                                    openTextField = true
                                }
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                } else {
                    Spacer(modifier = Modifier.height(16.dp))
                    TextField(
                        value = text,
                        onValueChange = { text = it },
                        placeholder = {
                            Text(
                                text = stringResource(R.string.your_acc_message),
                                style = MaterialTheme.typography.Text1New,
                                color = Grey3
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .height(85.dp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Black,
                            unfocusedContainerColor = Black,
                            cursorColor = White,
                            focusedTextColor = White,
                            unfocusedTextColor = White,
                            focusedIndicatorColor = Black,
                            unfocusedIndicatorColor = Black,
                        )
                    )
                    Spacer(modifier = Modifier.height(11.dp))
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp)
                ) {
                    RespondVacancyNew {
                        openTextField = false
                        onDismissRequest()
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Composable
private fun EditDialogWindow() {
    val dialogWindowProvider = LocalView.current.parent as? DialogWindowProvider

    dialogWindowProvider?.window?.setGravity(Gravity.BOTTOM)
}