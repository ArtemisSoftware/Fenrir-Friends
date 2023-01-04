package com.artemissoftware.fenrirfriends.navigation.navtypes

import android.os.Build
import android.os.Bundle
import androidx.navigation.NavType
import com.artemissoftware.fenrirfriends.screen.models.BreedUi
import com.google.gson.Gson

class BreedUiNavType : NavType<BreedUi>(isNullableAllowed = true) {
    override fun get(bundle: Bundle, key: String): BreedUi? {

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(key, BreedUi::class.java)
        } else {
            bundle.getParcelable(key)
        }
    }

    override fun put(bundle: Bundle, key: String, value: BreedUi) {
        bundle.putParcelable(key, value)
    }

    override fun parseValue(value: String): BreedUi {
        return Gson().fromJson(value, BreedUi::class.java)
    }

}