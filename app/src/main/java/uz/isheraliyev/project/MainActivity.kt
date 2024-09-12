package uz.isheraliyev.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState
import dagger.hilt.android.AndroidEntryPoint
import io.morfly.compose.bottomsheet.material3.BottomSheetScaffold
import io.morfly.compose.bottomsheet.material3.rememberBottomSheetScaffoldState
import io.morfly.compose.bottomsheet.material3.rememberBottomSheetState
import io.morfly.compose.bottomsheet.material3.requireSheetVisibleHeightDp
import uz.isheraliyev.project.ui.design.theme.ProjectTheme
import uz.isheraliyev.project.ui.screens.map.MapScreen
import uz.isheraliyev.project.ui.sheets.LocationsBottomSheet
import uz.isheraliyev.project.ui.sheets.SheetValue

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val sheetState = rememberBottomSheetState(
                initialValue = SheetValue.PartiallyExpanded,
                defineValues = {
                    SheetValue.Collapsed at height(100.dp)
                    SheetValue.PartiallyExpanded at offset(percent = 60)
                    SheetValue.Expanded at contentHeight
                }
            )

            val scaffoldState = rememberBottomSheetScaffoldState(sheetState)

            BottomSheetScaffold(
                scaffoldState = scaffoldState,
                sheetContent = {

                },
                content = {
                    val bottomPadding by remember {
                        derivedStateOf { sheetState.requireSheetVisibleHeightDp() }
                    }

                    MapScreen(
                        bottomPaddingValues = bottomPadding
                    )
                }
            )
        }
    }
}