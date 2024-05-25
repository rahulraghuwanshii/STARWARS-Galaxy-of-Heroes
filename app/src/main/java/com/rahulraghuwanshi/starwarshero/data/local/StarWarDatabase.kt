package com.rahulraghuwanshi.starwarshero.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rahulraghuwanshi.starwarshero.data.local.converters.StringListTypeConverter
import com.rahulraghuwanshi.starwarshero.domain.model.Character
import com.rahulraghuwanshi.starwarshero.domain.model.RemoteKeys

@Database(
    entities = [Character::class, RemoteKeys::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(StringListTypeConverter::class)
abstract class StarWarDatabase : RoomDatabase() {
    abstract fun starWarDao(): StarWarDao

    abstract fun remoteKeyDao(): RemoteKeysDao
}