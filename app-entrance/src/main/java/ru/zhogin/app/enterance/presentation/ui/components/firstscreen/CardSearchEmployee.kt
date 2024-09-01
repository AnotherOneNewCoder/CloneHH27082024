package ru.zhogin.app.enterance.presentation.ui.components.firstscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.zhogin.app.entrance.R
import ru.zhogin.app.uikit.ButtonText2
import ru.zhogin.app.uikit.Green
import ru.zhogin.app.uikit.Grey1
import ru.zhogin.app.uikit.Title3

@Composable
internal fun CardSearchEmployee() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 21.dp),
        shape = RoundedCornerShape(11.dp),
        colors = CardDefaults.cardColors(
            containerColor = Grey1
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 21.dp),
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = stringResource(R.string.find_employee),
                style = MaterialTheme.typography.Title3)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = stringResource(R.string.post_job_and_get_access_to_cv_base),
                style = MaterialTheme.typography.ButtonText2)
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {  },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(66.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Green
                )
            ) {
                Text(text = stringResource(R.string.i_am_searching_for_employee),
                    style = MaterialTheme.typography.ButtonText2)
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
@Preview
fun CardSearchEmplPrew() {
    CardSearchEmployee()
}