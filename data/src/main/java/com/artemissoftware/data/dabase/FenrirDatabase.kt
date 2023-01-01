package com.artemissoftware.data.dabase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.artemissoftware.data.dabase.dao.BreedDao
import com.artemissoftware.data.dabase.entities.BreedEntity


@Database(
    entities = [BreedEntity::class],
    version = 1
)
abstract class FenrirDatabase: RoomDatabase() {

    abstract val breedDao: BreedDao
}