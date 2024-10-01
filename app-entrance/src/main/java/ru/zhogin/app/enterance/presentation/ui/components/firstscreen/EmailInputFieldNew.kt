package ru.zhogin.app.enterance.presentation.ui.components.firstscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import ru.zhogin.app.entrance.R
import ru.zhogin.app.uikit.Grey2
import ru.zhogin.app.uikit.Grey4
import ru.zhogin.app.uikit.Red
import ru.zhogin.app.uikit.Text1New
import ru.zhogin.app.uikit.White

@Composable
fun EmailInputFieldNew(
    email: String,
    changeEmail: (String) -> Unit,
    clearFocus: () -> Unit,
    error: Boolean,
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
        //.clip(RoundedCornerShape(11.dp))
        ,
        value = email,
        onValueChange = changeEmail,
        textStyle = MaterialTheme.typography.Text1New,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {
            clearFocus()
        }),
        singleLine = true,
        placeholder = {
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.icon_email),
                    contentDescription = "email",
                    tint = Grey4,
                    //modifier = Modifier.padding(top = 3.dp)
                )
                Spacer(modifier = Modifier.width(17.dp))
                androidx.compose.material3.Text(
                    text = stringResource(R.string.email_or_phone),
                    style = MaterialTheme.typography.Text1New,
                    color = Grey4
                )
            }
        },
        trailingIcon = {
            if (email.isNotEmpty()) {
                Icon(
                    modifier = Modifier.clickable {
                        changeEmail("")
                    },
                    imageVector = Icons.Rounded.Close,
                    contentDescription = "Close Icon",
                    tint = White,
                )
            }
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = if (error) Red else Color.Transparent,
            unfocusedBorderColor = if (error) Red else Color.Transparent,
            focusedContainerColor = Grey2,
            unfocusedContainerColor = Grey2,
            cursorColor = White,
        )
    )
}