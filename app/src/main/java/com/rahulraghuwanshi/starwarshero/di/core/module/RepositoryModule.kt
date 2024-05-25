package com.rahulraghuwanshi.starwarshero.di.core.module

import com.rahulraghuwanshi.starwarshero.data.repository.StarWarRepositoryImpl
import com.rahulraghuwanshi.starwarshero.data.repository.dataSource.StarWarRemoteDataSource
import com.rahulraghuwanshi.starwarshero.domain.repository.StarWarRepository
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