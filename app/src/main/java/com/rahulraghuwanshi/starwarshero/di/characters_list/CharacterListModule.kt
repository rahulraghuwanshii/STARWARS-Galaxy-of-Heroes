package com.rahulraghuwanshi.starwarshero.di.characters_list

import com.rahulraghuwanshi.starwarshero.data.local.StarWarDatabase
import com.rahulraghuwanshi.starwarshero.domain.use_case.FetchCharactersListUseCase
import com.rahulraghuwanshi.starwarshero.presentation.characters_list.CharactersViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class CharacterListModule {
    @CharacterListScope
    @Provides
    fun provideCharactersViewModelFactory(
        fetchCharactersListUseCase: FetchCharactersListUseCase,
        starWarDatabase: StarWarDatabase
    ): CharactersViewModelFactory {
        return CharactersViewModelFactory(
            fetchCharactersListUseCase = fetchCharactersListUseCase,
            starWarDatabase = starWarDatabase
        )
    }

}