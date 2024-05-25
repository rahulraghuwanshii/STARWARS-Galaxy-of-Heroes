package com.rahulraghuwanshi.starwarshero.di.character_detail

import com.rahulraghuwanshi.starwarshero.domain.use_case.FetchCharacterHomeWorldUseCase
import com.rahulraghuwanshi.starwarshero.domain.use_case.FetchCharacterMoviesUseCase
import com.rahulraghuwanshi.starwarshero.presentation.character_detail.CharacterDetailViewModelFactory
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