package com.rahulraghuwanshi.starwarshero.di.core.module

import com.rahulraghuwanshi.starwarshero.data.network.StarWarApiInterface
import com.rahulraghuwanshi.starwarshero.data.repository.dataSource.StarWarRemoteDataSource
import com.rahulraghuwanshi.starwarshero.data.repository.datasourceImpl.StarWarRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteDataModule {
    @Provides
    @Singleton
    fun provideStarWarRemoteDataSource(starWarApiInterface: StarWarApiInterface): StarWarRemoteDataSource {
        return StarWarRemoteDataSourceImpl(apiInterface = starWarApiInterface)
    }
}