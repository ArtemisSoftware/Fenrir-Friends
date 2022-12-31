package com.artemissoftware.core_ui.composables.connectivity

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemissoftware.core_ui.R
import com.artemissoftware.core_ui.composables.connectivity.models.FFConnectionState
import com.artemissoftware.core_ui.composables.text.FFText
import com.artemissoftware.core_ui.theme.ErrorRed
import com.artemissoftware.core_ui.theme.SuccessGreen
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay


@ExperimentalAnimationApi
@ExperimentalCoroutinesApi
@Composable
fun FFConnectivityStatus() {
    val connection by FFConnectivityState()
    val isConnected = connection == FFConnectionState.Available
    var visibility by remember { mutableStateOf(false) }

    AnimatedVisibility(
        visible = visibility,
        enter = expandVertically(),
        exit = shrinkVertically()
    ) {
        ConnectivityStatusBox(isConnected = isConnected)
    }

    LaunchedEffect(isConnected) {
        visibility = if (!isConnected) {
            true
        } else {
            delay(2000)
            false
        }
    }
}



@Composable
private fun ConnectivityStatusBox(
    isConnected: Boolean
) {
    val backgroundColor by animateColorAsState(targetValue = if (isConnected) SuccessGreen else ErrorRed)
    val message = if (isConnected) stringResource(R.string.back_online) else stringResource(R.string.no_internet_connection)

    val iconResource = if (isConnected) {
        R.drawable.ic_connectivity_available
    } else {
        R.drawable.ic_connectivity_unavailable
    }

    Box(
        modifier = Modifier
            .background(backgroundColor)
            .fillMaxWidth()
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = iconResource),
                contentDescription = "",
                tint = Color.White
            )
            Spacer(modifier = Modifier.size(8.dp))
            FFText(
                text = message,
                color = Color.White
            )
        }
    }
}


@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    Column() {
        ConnectivityStatusBox(true)

        Spacer(modifier = Modifier.height(8.dp))
        ConnectivityStatusBox(false)

        Spacer(modifier = Modifier.height(8.dp))
        FFConnectivityStatus()
    }
}