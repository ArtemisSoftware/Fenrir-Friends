package com.artemissoftware.data.dabase.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.artemissoftware.data.dabase.entities.BreedEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BreedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBreeds(breeds: List<BreedEntity>)

    @Query("SELECT * FROM breedentity WHERE name LIKE '%' || :name || '%'")
    fun getBreeds(name: String): Flow<List<BreedEntity>>

    @Query("SELECT * FROM breedentity")
    fun getBreeds(): Flow<List<BreedEntity>>

    @Query("SELECT * FROM breedentity ORDER BY id ASC")
    fun getAllHeroes(): PagingSource<Int, BreedEntity>

    @Query("DELETE FROM breedentity")
    suspend fun deleteBreeds()
}