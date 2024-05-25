package com.rahulraghuwanshi.starwarshero.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rahulraghuwanshi.starwarshero.domain.model.RemoteKeys

/**
 * When remote keys are not directly associated with list items,
 * it is best to store them in a separate table in the local database.
 * While this can be done in the CharacterDetails table, creating a new table for the next and previous remote keys
 * associated with a CharacterDetails allows us to have a better separation of concerns.
 */
@Dao
interface RemoteKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<RemoteKeys>)

    @Query("Select * From remote_key Where character_id = :uuId")
    suspend fun getRemoteKeyByCharacterID(uuId: String): RemoteKeys?

    @Query("Delete From remote_key")
    suspend fun clearRemoteKeys()

    @Query("Select created_at From remote_key Order By created_at DESC LIMIT 1")
    suspend fun getCreationTime(): Long?
}