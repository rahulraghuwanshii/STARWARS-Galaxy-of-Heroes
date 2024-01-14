package com.rahulraghuwanshi.mystartheros.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rahulraghuwanshi.mystartheros.domain.model.Character

@Dao
interface StarWarDao {
    @Query("Select * from starWarCharactersTables ORDER by id")
    fun getAllCharacters(): PagingSource<Int, Character>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCharacters(charactersDetailList: List<Character>)

    @Query("DELETE FROM starWarCharactersTables")
    suspend fun clearAllCharacters()
}