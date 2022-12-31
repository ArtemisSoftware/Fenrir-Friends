package com.artemissoftware.core_ui.composables.scaffold

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.artemissoftware.core_ui.composables.indicator.FFIndicator
import com.artemissoftware.core_ui.composables.loading.FFLoading


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FFBottomSheetScaffold(
    isLoading: Boolean = false,
    sheetShape: Shape = MaterialTheme.shapes.large,
    sheetContent: @Composable ColumnScope.() -> Unit,
    content: @Composable (PaddingValues) -> Unit,
    toolbar: @Composable () -> Unit = {},
) {

    val sheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed,
    )
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        BottomSheetScaffold(
            scaffoldState = scaffoldState,
            sheetShape = sheetShape,
            sheetPeekHeight =  42.dp,
            sheetContent = {

                BottomSheetContent(sheetContent = sheetContent)
            },
            content = content
        )

        toolbar()

        FFLoading(isLoading = isLoading)
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

