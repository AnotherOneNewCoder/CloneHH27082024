package ru.zhogin.app.search.presentation.ui.components.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ru.zhogin.app.search.R
import ru.zhogin.app.uikit.Grey2
import ru.zhogin.app.uikit.Grey4
import ru.zhogin.app.uikit.Text1New
import ru.zhogin.app.uikit.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SearchBar(
    value: String,
    modifier: Modifier = Modifier,
    onValueChanged: (String) -> Unit,
    placeHolder: String,
    enable: Boolean,
    readOnly: Boolean,
    singleLine: Boolean,
    showAll: Boolean,
    hideVacancies: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    BasicTextField(
        value = value,
        onValueChange = onValueChanged,
        modifier = modifier,
        interactionSource = interactionSource,
        enabled = enable,
        readOnly = readOnly,
        singleLine = singleLine,
    ) { innerTextField ->
        TextFieldDefaults.DecorationBox(
            value = value,
            innerTextField = innerTextField,
            enabled = enable,
            singleLine = singleLine,
            visualTransformation = VisualTransformation.None,
            interactionSource = interactionSource,
            contentPadding = PaddingValues(0.dp),
            placeholder = {
                Text(
                    text = placeHolder,
                    style = MaterialTheme.typography.Text1New,
                    color = Grey4,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    )
            },
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Grey2,
                unfocusedContainerColor = Grey2,
                disabledContainerColor = Grey2
                ),
            leadingIcon = {
                Icon(
                    painter = if (!showAll) painterResource(id = R.drawable.icon_search)
                    else painterResource(id = R.drawable.icon_back),
                    contentDescription = "search",
                    tint = if (!showAll) Grey4 else White,
                    modifier = Modifier
                        .clickable(
                            enabled = showAll,
                            onClick = {
                                hideVacancies()
                            }
                        )
                        ,
                )
            }
        )
    }
}