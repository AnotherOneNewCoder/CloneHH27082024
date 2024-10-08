package ru.zhogin.app.enterance.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.zhogin.app.enterance.common.isValidEmail
import ru.zhogin.app.enterance.presentation.ui.components.firstscreen.CardSearchEmployeeNew
import ru.zhogin.app.enterance.presentation.ui.components.firstscreen.CardSearchJobNew
import ru.zhogin.app.enterance.presentation.ui.components.firstscreen.HardcoreTextEnterNew
import ru.zhogin.app.entrance.R
import ru.zhogin.app.uikit.Black

@Composable
internal fun FirstEntranceNew(
    modifier: Modifier,
    moveToSecondEntrance: (String) -> Unit,
) {


    var email by rememberSaveable {
        mutableStateOf("")
    }

    var error by rememberSaveable {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Black)
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        HardcoreTextEnterNew(
            text = stringResource(id = R.string.enter_in_lk)
        )
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Transparent),
        contentAlignment = Alignment.Center
    ) {
        Column {
            CardSearchJobNew(
                email = email,
                changeEmail = {
                    email = it
                    error = false
                },
                moveToSecondEntrance = {
                    if (isValidEmail(it)) {
                        moveToSecondEntrance(it)
                    } else {
                        error = true
                    }

                },
                error = error
            )
            Spacer(modifier = Modifier.height(16.dp))
            CardSearchEmployeeNew()
        }
    }
}


