package uz.isheraliyev.project.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import uz.isheraliyev.project.R
import uz.isheraliyev.project.ui.components.button.FloatingActionButton

@Composable
fun MapScreen(
    cameraPositionState: CameraPositionState,
    uiSettings: MapUiSettings,
    bottomPadding: Dp,
    modifier: Modifier = Modifier,
    isShowCurrentLocationVisible: Boolean,
    onLocationClick: () -> Unit
) {
    Box(modifier = modifier) {
        GoogleMap(
            modifier = Modifier.matchParentSize(),
            cameraPositionState = cameraPositionState,
            uiSettings = uiSettings,
            properties = MapProperties(
                isMyLocationEnabled = true,
                isBuildingEnabled = true
            ),
            contentPadding = PaddingValues(bottom = bottomPadding),
        )

        FloatingActionButton(
            painter = painterResource(id = R.drawable.ic_menu),
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(20.dp)
                .statusBarsPadding()
        )

        FloatingActionButton(
            painter = painterResource(id = R.drawable.ic_cancel),
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(20.dp)
                .statusBarsPadding()
        )

        if (isShowCurrentLocationVisible)
            FloatingActionButton(
                painter = painterResource(id = R.drawable.ic_location),
                onClick = onLocationClick,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(20.dp)
                    .padding(bottom = bottomPadding)
            )
    }
}