package uz.isheraliyev.project.ui.screens.map

import com.google.maps.android.compose.MapProperties

data class MapScreenState(
    val properties: MapProperties = MapProperties(
        isBuildingEnabled = true,
        isMyLocationEnabled = true,
        isIndoorEnabled = true
    )
)
