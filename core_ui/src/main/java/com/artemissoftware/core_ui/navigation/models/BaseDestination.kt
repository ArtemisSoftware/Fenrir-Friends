package com.artemissoftware.core_ui.navigation.models

import android.net.Uri
import com.google.gson.Gson


abstract class BaseDestination(
    val route: String,
    private val customArguments: List<CustomNavigationArgument> = emptyList()
) {
    //TODO: rever este nome
    fun getRouteInFull(): String {
        return if(customArguments.isEmpty()) route else fullRoute
    }

    private val fullRoute: String = buildString {
        append(route)
        customArguments.forEachIndexed { index, custom ->
            val symbol = if (index == 0) "?" else "&"
            append("$symbol${custom.key}={${custom.key}}")
        }
    }

    fun withArgs(vararg args: Any?): String {
        return buildString {
            append(route)
            args.forEachIndexed { index, arg ->
                val symbol = if (index == 0) "?" else "&"
                append("$symbol${customArguments[index].key}=$arg")
            }
        }
    }

    fun withCustomArgs(vararg args: Any?): String {

        return buildString {
            append(route)
            args.forEachIndexed { index, arg ->
                val json = Uri.encode(Gson().toJson(arg))
                val symbol = if (index == 0) "?" else "&"
                append("$symbol${customArguments[index].key}=$json")
            }
        }
    }

}