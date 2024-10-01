package ru.zhogin.app.search.presentation.ui.components.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.zhogin.app.search.R
import ru.zhogin.app.uikit.Grey2
import ru.zhogin.app.uikit.White


@Composable
internal fun SearchAndFiltersNew(
    showAll: Boolean,
    hideVacancies: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        SearchBar(
            modifier = Modifier
                .weight(1f)
                .height(40.dp)
            ,
            value = "",
            onValueChanged = {},
            placeHolder = stringResource(R.string.position_keywords),
            enable = false,
            readOnly = true,
            singleLine = true,
            showAll = showAll,
            hideVacancies = hideVacancies
        )


        Spacer(modifier = Modifier.width(8.dp))
        Button(
            modifier = Modifier.size(40.dp),
            onClick = {},
            enabled = false,
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Grey2,
                disabledContainerColor = Grey2
            ),
            contentPadding = PaddingValues(8.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_filter),
                contentDescription = "filters",
                tint = White,
                modifier = Modifier.size(24.dp)
            )
        }


    }
}