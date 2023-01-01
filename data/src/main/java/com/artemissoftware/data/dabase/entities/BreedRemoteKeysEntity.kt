package com.artemissoftware.data.dabase.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BreedRemoteKeysEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?,
    val lastUpdated: Long? = null
)