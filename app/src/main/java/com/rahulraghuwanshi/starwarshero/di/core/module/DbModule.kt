package com.rahulraghuwanshi.starwarshero.di.core.module

import android.content.Context
import androidx.room.Room
import com.rahulraghuwanshi.starwarshero.data.local.StarWarDao
import com.rahulraghuwanshi.starwarshero.data.local.StarWarDatabase
import com.rahulraghuwanshi.starwarshero.util.AppConstants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule {

    /**
     * The method returns the Database object
     * */
    @Provides
    @Singleton
    internal fun provideDatabase(context: Context): StarWarDatabase {
        return Room.databaseBuilder(
            context,
            StarWarDatabase::class.java,
            AppConstants.DATABASE_NAME
        )
            .build()
    }


    /**
     * We need the StarWarDao.
     * For this, We need the StarWarDatabase object
     * So we will define the providers for this here in this module.
     * */
    @Provides
    @Singleton
    internal fun provideStarWarDao(starWarDatabase: StarWarDatabase): StarWarDao {
        return starWarDatabase.starWarDao()
    }
}