package ru.zhogin.app.enterance.presentation.ui.components.secondscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import ru.zhogin.app.uikit.Grey2
import ru.zhogin.app.uikit.Title1
import ru.zhogin.app.uikit.White


@Composable
internal fun OptZone(
    textList: List<MutableState<TextFieldValue>>,
    requesterList: List<FocusRequester>,
    verifyCode: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row {
            for(i in textList.indices) {
                InputView(value = textList[i].value,
                    onValueChange = { newValue ->
                        // if old value is empty - return
                        if (textList[i].value.text != "") {
                            if (newValue.text == "") {
                                // before return if previous value is empty - set text filed to empty
                                textList[i].value = TextFieldValue(
                                    text = "",
                                    selection = TextRange(0)
                                )
                            }

                            return@InputView
                        }
                        // set new value and move cursor to the end
                        textList[i].value = TextFieldValue(
                            text = newValue.text,
                            selection = TextRange(newValue.text.length)
                        )
                        connectInputCode(textList = textList,
                            verifyCode = {
                                focusManager.clearFocus()
                                keyboardController?.hide()
                                verifyCode(it)
                            })
                        nextFocus(textList, requesterList)
                    },
                    focusRequester = requesterList[i],
                    clearFocus = {
                        focusManager.clearFocus()
                    }
                )
            }
        }
    }
    LaunchedEffect(key1 = null) {
        delay(300)
        requesterList[0].requestFocus()
    }
}
private fun connectInputCode(
    textList: List<MutableState<TextFieldValue>>,
    verifyCode: (String) -> Unit,
) {
    var code = ""
    for (text in textList) {
        code += text.value.text
    }
    if (code.length == 4) {
        verifyCode(code)
    }
}

private fun nextFocus(textList: List<MutableState<TextFieldValue>>, requesterList: List<FocusRequester>) {
    for (index in textList.indices) {
        if (textList[index].value.text == "") {
            if (index < textList.size) {
                requesterList[index].requestFocus()
                break
            }
        }
    }
}

@Composable
private fun InputView(
    value: TextFieldValue,
    onValueChange: (value: TextFieldValue) -> Unit,
    focusRequester: FocusRequester,
    clearFocus: () -> Unit,
) {
    BasicTextField(
        readOnly = false,
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .padding(end = 8.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Grey2)
            .wrapContentSize()
            .focusRequester(focusRequester),
        maxLines = 1,
        decorationBox = { innerTextField ->
            Box(modifier = Modifier
                .width(64.dp)
                .height(64.dp),
                contentAlignment = Alignment.Center
            ) {
                innerTextField()
            }
        },
        cursorBrush = SolidColor(White),
        textStyle = MaterialTheme.typography.Title1.copy(
            textAlign = TextAlign.Center
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = { clearFocus() }
        )
    )
}