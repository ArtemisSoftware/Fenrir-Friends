package com.artemissoftware.core_ui.composables.connectivity

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import androidx.compose.ui.platform.LocalContext
import com.artemissoftware.core_ui.composables.connectivity.models.FFConnectionState
import com.artemissoftware.core_ui.extensions.currentConnectivityState
import com.artemissoftware.core_ui.extensions.observeConnectivityAsFlow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.distinctUntilChanged


@ExperimentalCoroutinesApi
@Composable
fun FFConnectivityState(): State<FFConnectionState> {

    val context = LocalContext.current
    return produceState(
        initialValue = context.currentConnectivityState
    ) {
        context.observeConnectivityAsFlow().distinctUntilChanged().collect { value = it }
    }
}