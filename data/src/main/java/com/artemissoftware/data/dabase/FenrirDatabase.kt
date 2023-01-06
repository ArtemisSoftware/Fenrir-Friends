package com.artemissoftware.data.dabase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.artemissoftware.data.dabase.dao.BreedDao
import com.artemissoftware.data.dabase.dao.BreedRemoteKeysDao
import com.artemissoftware.data.dabase.entities.BreedEntity
import com.artemissoftware.data.dabase.entities.BreedRemoteKeysEntity


@Database(
    entities = [BreedEntity::class, BreedRemoteKeysEntity::class],
    version = 1,
    exportSchema = true
)
abstract class FenrirDatabase: RoomDatabase() {

    abstract val breedDao: BreedDao
    abstract val breedRemoteKeysDao: BreedRemoteKeysDao
}