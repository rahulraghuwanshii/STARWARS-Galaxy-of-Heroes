package com.rahulraghuwanshi.mystartheros.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rahulraghuwanshi.mystartheros.data.local.converters.StringListTypeConverter
import com.rahulraghuwanshi.mystartheros.domain.model.Character
import com.rahulraghuwanshi.mystartheros.domain.model.RemoteKeys

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