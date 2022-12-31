package com.artemissoftware.core_ui.composables.util

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import com.artemissoftware.core_ui.composables.connectivity.models.FFConnectionState

object NetworkUtil {

    fun networkConnectivityCallback(callback: (FFConnectionState) -> Unit): ConnectivityManager.NetworkCallback {

        return object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                callback(FFConnectionState.Available)
            }

            override fun onLost(network: Network) {
                callback(FFConnectionState.Unavailable)
            }
        }
    }

    fun getCurrentConnectivityState(connectivityManager: ConnectivityManager) : FFConnectionState {

        val connected = connectivityManager.allNetworks.any { network ->

            connectivityManager.getNetworkCapabilities(network)
                ?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                ?: false
        }

        return if (connected) FFConnectionState.Available else FFConnectionState.Unavailable
    }
}