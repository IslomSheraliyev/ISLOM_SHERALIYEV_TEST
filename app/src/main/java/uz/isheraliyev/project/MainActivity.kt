package uz.isheraliyev.project

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState
import dagger.hilt.android.AndroidEntryPoint
import io.morfly.compose.bottomsheet.material3.BottomSheetScaffold
import io.morfly.compose.bottomsheet.material3.rememberBottomSheetScaffoldState
import io.morfly.compose.bottomsheet.material3.rememberBottomSheetState
import io.morfly.compose.bottomsheet.material3.requireSheetVisibleHeightDp
import kotlinx.coroutines.launch
import uz.isheraliyev.project.ui.design.theme.LocalTheme
import uz.isheraliyev.project.ui.design.theme.ProjectTheme
import uz.isheraliyev.project.ui.screen.MapScreen
import uz.isheraliyev.project.ui.sheets.LocationsBottomSheet
import uz.isheraliyev.project.ui.sheets.SheetValue

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    @OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        setContent {
            ProjectTheme {
                val cameraPositionState = rememberCameraPositionState()
                var location by remember { mutableStateOf<LatLng?>(null) }
                val scope = rememberCoroutineScope()
                val locationPermissionRequest = rememberLauncherForActivityResult(
                    ActivityResultContracts.RequestPermission()
                ) { isGranted: Boolean ->
                    if (isGranted) {
                        fetchLocationAndMoveCamera(cameraPositionState) { latLng ->
                            location = latLng
                        }
                    }
                }

                LaunchedEffect(Unit) {
                    if (ActivityCompat.checkSelfPermission(
                            this@MainActivity,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        locationPermissionRequest.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                    } else {
                        fetchLocationAndMoveCamera(cameraPositionState) { latLng ->
                            location = latLng
                        }
                    }
                }

                val sheetState = rememberBottomSheetState(
                    initialValue = SheetValue.PartiallyExpanded,
                    defineValues = {
                        SheetValue.Collapsed at height(100.dp)
                        SheetValue.PartiallyExpanded at offset(percent = 60)
                        SheetValue.Expanded at contentHeight
                    }
                )

                val scaffoldState = rememberBottomSheetScaffoldState(sheetState)

                var dropDownVisibility by remember { mutableStateOf(false) }
                var selectedDropDownItem by remember {
                    mutableStateOf(
                        Pair(
                            R.drawable.ic_bus,
                            "Bus"
                        )
                    )
                }
                val dropDownOptions by remember {
                    mutableStateOf(
                        listOf(
                            Pair(R.drawable.ic_bus, "Bus"),
                            Pair(R.drawable.ic_taxi, "Taxi"),
                            Pair(R.drawable.ic_scooter, "Scooter"),
                            Pair(R.drawable.ic_bicycle, "Bicycle"),
                        )
                    )
                }

                val uiSettings by remember {
                    mutableStateOf(
                        MapUiSettings(
                            zoomControlsEnabled = false,
                            rotationGesturesEnabled = false,
                            myLocationButtonEnabled = false
                        )
                    )
                }

                BottomSheetScaffold(
                    scaffoldState = scaffoldState,
                    sheetContainerColor = LocalTheme.color.containerColor,
                    sheetDragHandle = null,
                    sheetShape = RectangleShape,
                    sheetContent = {
                        LocationsBottomSheet(
                            dropDownOptions = dropDownOptions,
                            dropDownVisibility = dropDownVisibility,
                            selectedDropDownItem = selectedDropDownItem,
                            onSelectDropDownItem = {
                                selectedDropDownItem = it
                                dropDownVisibility = false
                            },
                            onDropDownStateChange = { visibility ->
                                dropDownVisibility = visibility
                            }
                        )
                    },
                    content = {
                        val bottomPadding by remember {
                            derivedStateOf { sheetState.requireSheetVisibleHeightDp() }
                        }

                        MapScreen(
                            modifier = Modifier.fillMaxSize(),
                            cameraPositionState = cameraPositionState,
                            uiSettings = uiSettings,
                            bottomPadding = bottomPadding,
                            isShowCurrentLocationVisible = sheetState.currentValue != SheetValue.Expanded,
                            onLocationClick = {
                                fetchLocationAndMoveCamera(cameraPositionState) { latLng ->
                                    location = latLng
                                }

                                scope.launch { sheetState.animateTo(SheetValue.PartiallyExpanded) }
                            }
                        )

                        LaunchedEffect(cameraPositionState.position) {
                            // Adjust the bottom sheet state when map is dragging
                            if (cameraPositionState.isMoving) {
                                sheetState.animateTo(SheetValue.Collapsed)
                            } else {
                                sheetState.animateTo(SheetValue.PartiallyExpanded)
                            }
                        }
                    }
                )
            }
        }
    }

    private fun fetchLocationAndMoveCamera(
        cameraPositionState: CameraPositionState,
        onLocationAvailable: (LatLng) -> Unit
    ) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                location?.let {
                    val latLng = LatLng(it.latitude, it.longitude)
                    cameraPositionState.position = CameraPosition.fromLatLngZoom(latLng, 15f)
                    onLocationAvailable(latLng)
                }
            }
        }
    }
}
