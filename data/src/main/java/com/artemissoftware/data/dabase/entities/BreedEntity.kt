package com.artemissoftware.data.dabase.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BreedEntity(
    val name: String,
    val url: String,
    val group: String? = null,
    val origin: String? = null,
    val temperament: String? = null,
    @PrimaryKey val id: Int
)