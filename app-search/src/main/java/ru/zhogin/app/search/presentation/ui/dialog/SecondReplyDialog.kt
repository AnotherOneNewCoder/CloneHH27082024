package ru.zhogin.app.search.presentation.ui.dialog

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import ru.zhogin.app.search.R
import ru.zhogin.app.search.presentation.ui.RespondVacancy
import ru.zhogin.app.uikit.Black
import ru.zhogin.app.uikit.Grey2
import ru.zhogin.app.uikit.Grey3
import ru.zhogin.app.uikit.Text1
import ru.zhogin.app.uikit.Title3
import ru.zhogin.app.uikit.White

@Composable
internal fun SecondReplyDialog(
    text: String,
    onDismissRequest: () -> Unit,
) {
    var textAcc by rememberSaveable {
        mutableStateOf(text)
    }
    Dialog(
        onDismissRequest = {
            onDismissRequest()
        },
        properties = DialogProperties(
            usePlatformDefaultWidth = false // experimental
        )
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(topStart = 11.dp, topEnd = 11.dp),
            colors = CardDefaults.cardColors(
                containerColor = Black
            ),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.height(21.dp))
                HorizontalDivider(
                    color = Grey2,
                    thickness = 7.dp,
                    modifier = Modifier
                        .width(51.dp)
                        .clip(RoundedCornerShape(13.dp))
                        .align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(32.dp))
                Row(
                    modifier = Modifier.padding(start = 21.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ellipse_avatar),
                        contentDescription = "avatar",
                        modifier = Modifier.size(64.dp)
                    )
                    Spacer(modifier = Modifier.width(21.dp))
                    Column {
                        Spacer(modifier = Modifier.height(3.dp))
                        Text(
                            text = stringResource(R.string.hardcore_cv_add),
                            style = MaterialTheme.typography.Text1,
                            color = Grey3,
                        )
                        Spacer(modifier = Modifier.height(10.5.dp))
                        Text(
                            text = stringResource(R.string.harcore_job_position),
                            style = MaterialTheme.typography.Title3,
                            color = White,
                        )
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))
                HorizontalDivider(
                    color = Grey2,
                    thickness = 1.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 21.dp)
                )
                Spacer(modifier = Modifier.height(21.dp))
                TextField(
                    value = textAcc,
                    onValueChange = { textAcc = it },
                    placeholder = {
                        Text(
                            text = stringResource(R.string.your_acc_message),
                            style = MaterialTheme.typography.Text1,
                            color = Grey3
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 21.dp)
                        .height(113.3.dp),
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
                Spacer(modifier = Modifier.height(14.6.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                ) {
                    RespondVacancy {
                        onDismissRequest()
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}