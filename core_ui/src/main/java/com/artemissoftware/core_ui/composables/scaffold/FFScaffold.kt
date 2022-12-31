package com.artemissoftware.core_ui.composables.scaffold

import androidx.annotation.RawRes
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.artemissoftware.core_ui.R
import com.artemissoftware.core_ui.composables.connectivity.FFConnectivityStatus
import com.artemissoftware.core_ui.composables.dialog.FFDialog
import com.artemissoftware.core_ui.composables.loading.FFLoading
import com.artemissoftware.core_ui.composables.navigationbar.FFBottomNavigationBar
import com.artemissoftware.core_ui.navigation.models.BottomBarItem

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun FFScaffold(
    modifier: Modifier = Modifier/*.statusBarsPadding()*/,
    ffUiScaffoldState: FFUiScaffoldState? = null,
    isLoading: Boolean = false,
    @RawRes lottieId: Int = R.raw.lottie_android_icon,
    navController: NavHostController? = null,
    bottomBarItems: List<BottomBarItem> = emptyList(),
    content: @Composable (PaddingValues) -> Unit,
    toolbar: @Composable () -> Unit = {},
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        FFConnectivityStatus()

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
                    modifier = Modifier,
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


        FFLoading(isLoading = isLoading, lottieId = lottieId)

        ffUiScaffoldState?.let { FFDialog(ffUiScaffoldState = it) }

    }
}

