package ru.zhogin.app.enterance.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.zhogin.app.uikit.Black
import ru.zhogin.app.uikit.Title1New

@Composable
fun ErrorBox(
    paddingValues: PaddingValues,
) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(Black),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Error...", style = MaterialTheme.typography.Title1New)
            Spacer(modifier = Modifier.height(21.dp))

        }

    }
}