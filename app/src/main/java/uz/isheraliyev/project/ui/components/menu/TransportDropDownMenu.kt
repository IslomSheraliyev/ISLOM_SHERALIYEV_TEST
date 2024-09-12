package uz.isheraliyev.project.ui.components.menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import uz.isheraliyev.project.ui.design.theme.LocalTheme

@Composable
fun TransportDropDownMenu(
    expanded: Boolean,
    onDismiss: () -> Unit,
    options: List<Pair<Int, String>>,
    selectedItem: Pair<Int, String>,
    onSelect: (Pair<Int, String>) -> Unit,
    modifier: Modifier = Modifier
) {
    DropdownMenu(
        modifier = modifier,
        expanded = expanded,
        shape = RoundedCornerShape(12.dp),
        onDismissRequest = onDismiss,
        containerColor = LocalTheme.color.menuColor
    ) {
        Column(
            modifier = Modifier
                .width(IntrinsicSize.Min)
                .widthIn(min = 120.dp)
                .padding(8.dp)
        ) {
            options.forEachIndexed { index, item ->
                DropdownMenuItem(
                    text = {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(8.dp),
                            colors = CardDefaults.cardColors(
                                if (item == selectedItem) LocalTheme.color.selectedItemColor else Color.Transparent
                            )
                        ) {
                            Column(
                                modifier = Modifier.width(IntrinsicSize.Min)
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(
                                        horizontal = 6.dp,
                                        vertical = 8.dp
                                    )
                                ) {
                                    Icon(
                                        painter = painterResource(id = item.first),
                                        contentDescription = null,
                                        tint = LocalTheme.color.textPrimary
                                    )

                                    Text(
                                        text = item.second,
                                        color = LocalTheme.color.textPrimary,
                                        style = LocalTheme.font.smallMedium
                                    )
                                }

                                if (item != selectedItem && index != options.lastIndex)
                                    HorizontalDivider(
                                        thickness = 1.dp,
                                        color = LocalTheme.color.cardBorder
                                    )
                            }
                        }
                    },
                    onClick = { onSelect(item) }
                )
            }
        }
    }
}