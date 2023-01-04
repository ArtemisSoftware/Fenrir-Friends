package com.artemissoftware.core_ui.navigation.models

import android.net.Uri
import androidx.navigation.NamedNavArgument
import androidx.navigation.navArgument
import com.google.gson.Gson


abstract class BaseDestination(
    val route: String,
    protected val customArguments: List<CustomNavigationArgument> = emptyList()
) {
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

    val arguments: List<NamedNavArgument> = customArguments.map {
        navArgument(it.key) {
            type = it.type
            nullable = it.nullable
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

    protected fun addQueryToRoute(vararg args: Any?): String {
        return buildString {
            append(route)
            args.forEachIndexed { index, arg ->
                val symbol = if (index == 0) "?" else "&"
                if(arg != null) {
                    append("$symbol${arg}")
                }
            }
        }
    }

    companion object{
        fun convertCustomObject(obj: Any): String{
            return Uri.encode(Gson().toJson(obj))
        }
    }
}