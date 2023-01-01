package com.artemissoftware.fenrirfriends.screen.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BreedUi(
    val id: Int,
    val name: String,
    val url: String,
    val group: String? = null,
    val origin: String? = null,
    val temperament: String? = null
) : Parcelable
