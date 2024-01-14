package com.rahulraghuwanshi.mystartheros.di.core.module

import com.rahulraghuwanshi.mystartheros.data.network.StarWarApiInterface
import com.rahulraghuwanshi.mystartheros.data.repository.dataSource.StarWarRemoteDataSource
import com.rahulraghuwanshi.mystartheros.data.repository.datasourceImpl.StarWarRemoteDataSourceImpl
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