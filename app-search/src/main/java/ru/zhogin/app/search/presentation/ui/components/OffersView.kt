package ru.zhogin.app.search.presentation.ui.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.zhogin.app.search.R
import ru.zhogin.app.search.domain.models.offer.Offer
import ru.zhogin.app.uikit.Blue
import ru.zhogin.app.uikit.DarkBlue
import ru.zhogin.app.uikit.DarkGreen
import ru.zhogin.app.uikit.Green
import ru.zhogin.app.uikit.Grey1
import ru.zhogin.app.uikit.Text1
import ru.zhogin.app.uikit.Title4

@Composable
internal fun OffersView(
    listOffers: List<Offer>
) {
    LazyRow(
        modifier = Modifier.padding(start = 21.dp)
    ) {
        items(listOffers.size) { count ->
            OfferView(offer = listOffers[count])
        }
    }

}

@Composable
private fun OfferView(
    offer: Offer
) {
    val ctx = LocalContext.current
    Card(
        modifier = Modifier
            .height(160.dp)
            .width(176.dp)
            .padding(end = 10.dp)
            .clickable(
                enabled = true,
                onClick = {
                    val urlIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(offer.link)
                    )
                    ctx.startActivity(urlIntent)
                }),
        shape = RoundedCornerShape(11.dp),
        colors = CardDefaults.cardColors(
            containerColor = Grey1
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp)
        ) {
            Spacer(modifier = Modifier.height(13.dp))
            if (offer.id != null) {
                IconButton(
                    onClick = { val urlIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(offer.link)
                    )
                        ctx.startActivity(urlIntent) }, colors = when(offer.id) {
                        "near_vacancies" -> {
                            IconButtonDefaults.iconButtonColors(
                                containerColor = DarkBlue
                            )
                        }
                        "level_up_resume" -> {
                            IconButtonDefaults.iconButtonColors(
                                containerColor = DarkGreen
                            )
                        }
                        "temporary_job" -> {
                            IconButtonDefaults.iconButtonColors(
                                containerColor = DarkGreen
                            )
                        }
                        else -> {
                            IconButtonDefaults.iconButtonColors()
                        }
                    },
                    modifier = Modifier.size(43.dp)
                ) {
                    Icon(
                        painter = painterResource(
                            id = when (offer.id) {
                                "near_vacancies" -> {
                                    R.drawable.icon_near
                                }
                                "level_up_resume" -> {
                                    R.drawable.icon_star
                                }
                                "temporary_job" -> {
                                    R.drawable.icon_list
                                }

                                else -> {R.drawable.icon_search}
                            }
                        ),
                        contentDescription = "Icon",
                        modifier = Modifier.size(32.dp),
                        tint = when(offer.id) {
                            "near_vacancies" -> Blue

                            "level_up_resume" -> Green

                            "temporary_job" -> Green

                            else -> Color.Transparent
                        }

                    )
                }
                Spacer(modifier = Modifier.height(21.dp))

            } else {
                Spacer(modifier = Modifier.height(64.dp))
            }
            Text(
                text = offer.title,
                style = MaterialTheme.typography.Title4,
                maxLines = if(offer.button == null) 3 else 2,
            )
            if (offer.button != null) {
                Text(
                    text = offer.button.text,
                    style = MaterialTheme.typography.Text1,
                    color = Green,
                )
            }

        }
    }
}