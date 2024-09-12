package uz.isheraliyev.project.ui.components.button

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import uz.isheraliyev.project.ui.design.theme.LocalTheme

@Composable
fun FloatingActionButton(
    painter: Painter,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(LocalTheme.color.containerColor)
    ) {
        IconButton(onClick = onClick) {
            Icon(
                painter = painter,
                contentDescription = null,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}