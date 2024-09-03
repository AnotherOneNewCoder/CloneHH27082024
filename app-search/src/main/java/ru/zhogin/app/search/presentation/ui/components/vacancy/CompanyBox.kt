package ru.zhogin.app.search.presentation.ui.components.vacancy

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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.zhogin.app.search.R
import ru.zhogin.app.uikit.Grey1
import ru.zhogin.app.uikit.Grey3
import ru.zhogin.app.uikit.Text1
import ru.zhogin.app.uikit.Title3

@Composable
internal fun CompanyBox(
    companyName: String,
    companyAddress: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(11.dp),
        colors = CardDefaults.cardColors(
            containerColor = Grey1
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 21.dp, vertical = 16.dp)
        ) {
            Row {
                Text(text = companyName, style = MaterialTheme.typography.Title3)
                Spacer(modifier = Modifier.width(11.dp))
                Icon(
                    painter = painterResource(id = R.drawable.icon_checked),
                    contentDescription = "checked",
                    modifier = Modifier
                        .size(21.dp)
                        .padding(top = 1.dp),
                    tint = Grey3
                )
            }
            Spacer(modifier = Modifier.height(11.dp))
            Image(
                painter = painterResource(id = R.drawable.company_map), contentDescription = "map",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(77.dp)
                    .clip(RoundedCornerShape(11.dp))
            )
            Spacer(modifier = Modifier.height(11.dp))
            Text(text = companyAddress, style = MaterialTheme.typography.Text1)
        }
    }

}
