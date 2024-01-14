package com.rahulraghuwanshi.mystartheros.di.character_detail

import com.rahulraghuwanshi.mystartheros.domain.use_case.FetchCharacterHomeWorldUseCase
import com.rahulraghuwanshi.mystartheros.domain.use_case.FetchCharacterMoviesUseCase
import com.rahulraghuwanshi.mystartheros.presentation.character_detail.CharacterDetailViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class CharacterDetailModule {
    @CharacterDetailScope
    @Provides
    fun provideCharacterDetailViewModelFactory(
        fetchCharacterMoviesUseCase: FetchCharacterMoviesUseCase,
        fetchCharacterHomeWorldUseCase: FetchCharacterHomeWorldUseCase
    ): CharacterDetailViewModelFactory {
        return CharacterDetailViewModelFactory(
            fetchCharacterHomeWorldUseCase = fetchCharacterHomeWorldUseCase,
            fetchCharacterMoviesUseCase = fetchCharacterMoviesUseCase
        )
    }

}