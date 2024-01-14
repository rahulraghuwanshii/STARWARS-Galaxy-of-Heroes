package com.rahulraghuwanshi.mystartheros.di.core.module

import com.rahulraghuwanshi.mystartheros.data.local.StarWarDatabase
import com.rahulraghuwanshi.mystartheros.data.repository.StarWarRepositoryImpl
import com.rahulraghuwanshi.mystartheros.data.repository.dataSource.StarWarRemoteDataSource
import com.rahulraghuwanshi.mystartheros.domain.repository.StarWarRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideStarWarRepository(
        starWarRemoteDataSource: StarWarRemoteDataSource,
    ): StarWarRepository {
        return StarWarRepositoryImpl(
            startWarRemoteDataSource = starWarRemoteDataSource,
        )
    }
}