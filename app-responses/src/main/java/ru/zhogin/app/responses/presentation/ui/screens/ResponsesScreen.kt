package ru.zhogin.app.responses.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.zhogin.app.responses.R
import ru.zhogin.app.uikit.Black
import ru.zhogin.app.uikit.Title1

@Composable
fun ResponsesScreen(
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Black),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(R.string.responses_screen),
            style = MaterialTheme.typography.Title1)
    }
}