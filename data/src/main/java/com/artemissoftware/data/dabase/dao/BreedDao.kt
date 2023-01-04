package com.artemissoftware.data.dabase.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.artemissoftware.data.dabase.entities.BreedEntity

@Dao
interface BreedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBreeds(breeds: List<BreedEntity>)


    @Query("SELECT * FROM breedentity WHERE id = :id")
    suspend fun getBreed(id: Int): BreedEntity?

    @Query("SELECT * FROM breedentity ORDER BY id ASC")
    fun getBreeds(): PagingSource<Int, BreedEntity>

    @Query("DELETE FROM breedentity")
    suspend fun deleteBreeds()
}