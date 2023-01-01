package com.artemissoftware.core_ui.composables.scaffold

import androidx.annotation.RawRes
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.artemissoftware.core_ui.R
import com.artemissoftware.core_ui.composables.connectivity.FFConnectivityStatus
import com.artemissoftware.core_ui.composables.dialog.FFDialog
import com.artemissoftware.core_ui.composables.loading.FFLoading
import com.artemissoftware.core_ui.composables.navigationbar.FFBottomNavigationBar
import com.artemissoftware.core_ui.navigation.models.BottomBarItem
import kotlin.math.roundToInt

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun FFScaffold(
    modifier: Modifier = Modifier/*.statusBarsPadding()*/,
    ffUiScaffoldState: FFUiScaffoldState? = null,
    isLoading: Boolean = false,
    @RawRes lottieId: Int = R.raw.lottie_fenris,
    navController: NavHostController? = null,
    bottomBarItems: List<BottomBarItem> = emptyList(),
    content: @Composable (PaddingValues) -> Unit,
    toolbar: @Composable () -> Unit = {},
) {


    val bottomBarHeight = 58.dp
    val bottomBarHeightPx = with(LocalDensity.current) {
        bottomBarHeight.roundToPx().toFloat()
    }
    val bottomBarOffsetHeightPx = remember { mutableStateOf(0f) }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                val newOffset = bottomBarOffsetHeightPx.value + delta
                bottomBarOffsetHeightPx.value = newOffset.coerceIn(-bottomBarHeightPx, 0f)
                return Offset.Zero
            }
        }
    }

    Box(
        modifier = Modifier
            .nestedScroll(nestedScrollConnection)
            .fillMaxSize()
    ) {


        var scaffoldModifier = modifier
            .fillMaxSize()
            .background(Color.White)


        Scaffold(
            modifier = scaffoldModifier,
            topBar = {
                toolbar.invoke()
            },
            bottomBar = {

                FFBottomNavigationBar(
                    modifier = Modifier
                        .height(bottomBarHeight)
                        .offset {
                            IntOffset(x = 0, y = -bottomBarOffsetHeightPx.value.roundToInt())
                        },
                    items = bottomBarItems,
                    navController = navController,
                    ffUiScaffoldState = ffUiScaffoldState,
                    onItemClick = {
                        ffUiScaffoldState?.changeCurrentPositionBottomBar(
                            destination = it.destination,
                            navController = navController
                        )
                    }
                )

            },
            content = content
        )


        FFConnectivityStatus()

        FFLoading(isLoading = isLoading, lottieId = lottieId)

        ffUiScaffoldState?.let { FFDialog(ffUiScaffoldState = it) }

    }
}

