package uz.isheraliyev.project.ui.sheets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import uz.isheraliyev.project.R
import uz.isheraliyev.project.ui.components.card.NearLocationCard
import uz.isheraliyev.project.ui.components.menu.TransportDropDownMenu
import uz.isheraliyev.project.ui.design.theme.LocalTheme

@Composable
fun LocationsBottomSheet(
    dropDownOptions: List<Pair<Int, String>>,
    dropDownVisibility: Boolean,
    selectedDropDownItem: Pair<Int, String>,
    onSelectDropDownItem: (Pair<Int, String>) -> Unit,
    onDropDownStateChange: (Boolean) -> Unit,
) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 20.dp,
                    vertical = 16.dp
                )
        ) {
            Card(
                shape = RoundedCornerShape(8.dp),
                onClick = { onDropDownStateChange(!dropDownVisibility) },
                colors = CardDefaults.cardColors(Color.Transparent)
            ) {
                Row(
                    modifier = Modifier.padding(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = selectedDropDownItem.first),
                        contentDescription = null,
                        tint = LocalTheme.color.label
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = selectedDropDownItem.second,
                        color = LocalTheme.color.label,
                        style = LocalTheme.font.largeSemiBold
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    Icon(
                        painter = painterResource(id = R.drawable.ic_dropdown),
                        contentDescription = null,
                        tint = LocalTheme.color.label,
                        modifier = if (dropDownVisibility) Modifier.rotate(180f) else Modifier
                    )
                }
            }

            TransportDropDownMenu(
                expanded = dropDownVisibility,
                onDismiss = { onDropDownStateChange(false) },
                options = dropDownOptions,
                selectedItem = selectedDropDownItem,
                onSelect = { selectedItem ->
                    onSelectDropDownItem(selectedItem)
                }
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = stringResource(id = R.string.display_distance),
                color = LocalTheme.color.label,
                style = LocalTheme.font.mediumMedium
            )

            Spacer(modifier = Modifier.width(10.dp))

            Card(
                colors = CardDefaults.cardColors(Color.Transparent),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(
                    width = 1.dp,
                    color = LocalTheme.color.cardBorder
                )
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(
                        horizontal = 8.dp,
                        vertical = 6.dp
                    )
                ) {
                    Text(
                        text = "75 M",
                        color = LocalTheme.color.label,
                        style = LocalTheme.font.smallMedium
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.ic_dropdown),
                        contentDescription = null,
                        tint = LocalTheme.color.label
                    )
                }
            }
        }

        HorizontalDivider(
            thickness = 1.dp,
            color = LocalTheme.color.cardBorder
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(5) {
                NearLocationCard()
            }
        }
    }
}


enum class SheetValue { Collapsed, PartiallyExpanded, Expanded }