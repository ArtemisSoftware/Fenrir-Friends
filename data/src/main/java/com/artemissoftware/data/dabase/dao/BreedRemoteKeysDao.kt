package com.artemissoftware.data.dabase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.artemissoftware.data.dabase.entities.BreedRemoteKeysEntity


@Dao
interface BreedRemoteKeysDao {

    @Query("SELECT * FROM breedremotekeysentity WHERE id =:breedId")
    suspend fun getRemoteKeys(breedId: Int): BreedRemoteKeysEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(heroRemoteKeys: List<BreedRemoteKeysEntity>)

    @Query("DELETE FROM breedremotekeysentity")
    suspend fun deleteAllRemoteKeys()

}