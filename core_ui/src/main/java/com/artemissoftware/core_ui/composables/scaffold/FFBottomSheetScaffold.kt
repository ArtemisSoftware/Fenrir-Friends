package com.artemissoftware.core_ui.composables.scaffold

import androidx.annotation.RawRes
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.artemissoftware.core_ui.R
import com.artemissoftware.core_ui.composables.connectivity.FFConnectivityStatus
import com.artemissoftware.core_ui.composables.indicator.FFIndicator
import com.artemissoftware.core_ui.composables.loading.FFLoading
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class, ExperimentalAnimationApi::class)
@Composable
fun FFBottomSheetScaffold(
    isLoading: Boolean = false,
    ffUiScaffoldState: FFUiScaffoldState? = null,
    showConnectivityStatus: Boolean = true,
    @RawRes lottieId: Int = R.raw.lottie_data_loading,
    sheetShape: Shape = MaterialTheme.shapes.large,
    sheetContent: @Composable ColumnScope.() -> Unit,
    content: @Composable (PaddingValues) -> Unit,
    toolbar: @Composable () -> Unit = {},
    bottomSheetExpanded: Boolean = false
) {

    val sheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed,
    )
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)

    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        BottomSheetScaffold(
            scaffoldState = scaffoldState,
            sheetShape = sheetShape,
            sheetPeekHeight =  42.dp,
            topBar = {
                Column {
                    toolbar.invoke()
                    if(showConnectivityStatus) FFConnectivityStatus()
                }
            },
            sheetContent = {

                BottomSheetContent(sheetContent = sheetContent)
            },
            content = content
        )


        ffUiScaffoldState?.let {
            if(it.bottomSheet.value) {
                coroutineScope.launch {

                    delay(it.bottomSheetDelay)

                    if (it.bottomSheet.value) scaffoldState.bottomSheetState.expand() else scaffoldState.bottomSheetState.collapse()
                }
            }
        }
        FFLoading(isLoading = isLoading, lottieId = lottieId)
    }
}


@Composable
private fun ColumnScope.BottomSheetContent(
    sheetContent: @Composable ColumnScope.() -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        FFIndicator()
    }
    sheetContent()
}

