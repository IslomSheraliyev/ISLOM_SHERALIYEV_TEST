package uz.isheraliyev.project.ui.components.card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import uz.isheraliyev.project.R
import uz.isheraliyev.project.ui.design.theme.LocalTheme

@Composable
fun NearLocationCard(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(size = 12.dp),
        colors = CardDefaults.cardColors(
            containerColor = LocalTheme.color.cardColor
        ),
        border = BorderStroke(
            color = LocalTheme.color.cardBorder,
            width = .5.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    Text(
                        text = stringResource(id = R.string.card_title),
                        color = LocalTheme.color.textPrimary,
                        style = LocalTheme.font.mediumSemiBold
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.height(IntrinsicSize.Min)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_walking_person),
                            contentDescription = null,
                            tint = LocalTheme.color.textPrimary
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        Text(
                            text = buildAnnotatedString {
                                withStyle(
                                    SpanStyle(
                                        color = LocalTheme.color.blue,
                                        fontSize = LocalTheme.font.smallRegular.fontSize,
                                        fontWeight = LocalTheme.font.smallRegular.fontWeight,
                                        fontFamily = LocalTheme.font.smallRegular.fontFamily
                                    )
                                ) {
                                    append((2).toString())
                                }

                                append(" ")

                                withStyle(
                                    SpanStyle(
                                        color = LocalTheme.color.textPrimary,
                                        fontSize = LocalTheme.font.smallRegular.fontSize,
                                        fontWeight = LocalTheme.font.smallRegular.fontWeight,
                                        fontFamily = LocalTheme.font.smallRegular.fontFamily
                                    )
                                ) {
                                    append("min")
                                }
                            }
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        VerticalDivider(
                            thickness = 1.dp,
                            modifier = Modifier.fillMaxHeight(),
                            color = LocalTheme.color.dividerColor,
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = "1 Km",
                            color = LocalTheme.color.textPrimary,
                            style = LocalTheme.font.smallRegular
                        )
                    }
                }

                Surface(
                    color = LocalTheme.color.blue,
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Icon(
                        modifier = Modifier.padding(6.dp),
                        painter = painterResource(id = R.drawable.ic_bus_filled),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }

            HorizontalDivider(
                thickness = 1.dp,
                color = LocalTheme.color.cardBorder
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(
                    shape = RoundedCornerShape(4.dp),
                    border = BorderStroke(
                        width = 1.dp,
                        color = LocalTheme.color.grey
                    ),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Transparent
                    )
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 4.dp, vertical = 6.dp),
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_bus_filled),
                            contentDescription = null,
                            tint = LocalTheme.color.textPrimary
                        )

                        Text(
                            text = stringResource(id = R.string.bus_number),
                            color = LocalTheme.color.textPrimary,
                            style = LocalTheme.font.smallMedium
                        )
                    }
                }

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = stringResource(id = R.string.bus_towards_to),
                    color = LocalTheme.color.textPrimary,
                    style = LocalTheme.font.smallMedium
                )

                Spacer(modifier = Modifier.weight(1f))

                Icon(
                    painter = painterResource(id = R.drawable.ic_wifi),
                    contentDescription = null,
                    tint = LocalTheme.color.blue
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = "2, 12, 24 min",
                    color = LocalTheme.color.textPrimary,
                    style = LocalTheme.font.smallSemiBold
                )
            }
        }
    }
}