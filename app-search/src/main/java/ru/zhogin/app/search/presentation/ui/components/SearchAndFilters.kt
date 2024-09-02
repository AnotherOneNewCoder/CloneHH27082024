package ru.zhogin.app.search.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ru.zhogin.app.search.R
import ru.zhogin.app.uikit.Grey2
import ru.zhogin.app.uikit.Grey4
import ru.zhogin.app.uikit.Text1
import ru.zhogin.app.uikit.White


@Composable
internal fun SearchAndFilters(
    showAll: Boolean,
    hideVacancies: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 21.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        OutlinedTextField(
            value = "",
            onValueChange = {},
            readOnly = true,
            enabled = false,
            singleLine = true,
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(11.dp)),
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
                        .padding(top = if (showAll) 3.dp else 0.dp),
                )
            },
            placeholder = {
                Text(
                    text = stringResource(R.string.position_keywords),
                    style = MaterialTheme.typography.Text1,
                    color = Grey4,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Grey2,
                unfocusedContainerColor = Grey2,
                disabledContainerColor = Grey2
            ),
        )
        Spacer(modifier = Modifier.width(13.dp))
        Button(
            onClick = {},
            enabled = false,
            shape = RoundedCornerShape(11.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Grey2,
                disabledContainerColor = Grey2
            ),
            contentPadding = PaddingValues(11.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_filter),
                contentDescription = "filters",
                tint = White,
                modifier = Modifier.size(32.dp)
            )
        }


    }
}