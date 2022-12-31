package com.artemissoftware.core_ui.extensions

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import com.artemissoftware.core_ui.composables.connectivity.models.FFConnectionState
import com.artemissoftware.core_ui.composables.util.NetworkUtil.getCurrentConnectivityState
import com.artemissoftware.core_ui.composables.util.NetworkUtil.networkConnectivityCallback
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

@ExperimentalCoroutinesApi
fun Context.observeConnectivityAsFlow(): Flow<FFConnectionState> = callbackFlow {

    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val callback = networkConnectivityCallback { connectionState -> trySend(connectionState)}

    val networkRequest = NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .build()

    connectivityManager.registerNetworkCallback(networkRequest, callback)

    trySend(getCurrentConnectivityState(connectivityManager))

    awaitClose {
        connectivityManager.unregisterNetworkCallback(callback)
    }
}

val Context.currentConnectivityState: FFConnectionState
    get() {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return getCurrentConnectivityState(connectivityManager)
    }