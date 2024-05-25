package com.rahulraghuwanshi.starwarshero.di.core.module

import com.rahulraghuwanshi.starwarshero.domain.repository.StarWarRepository
import com.rahulraghuwanshi.starwarshero.domain.use_case.FetchCharactersListUseCase
import com.rahulraghuwanshi.starwarshero.domain.use_case.FetchCharacterHomeWorldUseCase
import com.rahulraghuwanshi.starwarshero.domain.use_case.FetchCharacterMoviesUseCase
import com.rahulraghuwanshi.starwarshero.domain.use_case.impl.FetchCharactersListUseCaseImpl
import com.rahulraghuwanshi.starwarshero.domain.use_case.impl.FetchCharacterHomeWorldUseCaseImpl
import com.rahulraghuwanshi.starwarshero.domain.use_case.impl.FetchCharacterMoviesUseCaseImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Provides
    @Singleton
    fun provideFetchCharactersUseCase(starWarRepository: StarWarRepository): FetchCharactersListUseCase {
        return FetchCharactersListUseCaseImpl(starWarRepository = starWarRepository)
    }

    @Provides
    @Singleton
    fun provideFetchHomeWorldUseCase(starWarRepository: StarWarRepository): FetchCharacterHomeWorldUseCase {
        return FetchCharacterHomeWorldUseCaseImpl(starWarRepository = starWarRepository)
    }

    @Provides
    @Singleton
    fun provideFetchMoviesUseCase(starWarRepository: StarWarRepository): FetchCharacterMoviesUseCase {
        return FetchCharacterMoviesUseCaseImpl(starWarRepository = starWarRepository)
    }
}