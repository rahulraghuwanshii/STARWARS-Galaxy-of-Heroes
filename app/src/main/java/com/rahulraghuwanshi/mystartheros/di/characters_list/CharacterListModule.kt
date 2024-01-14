package com.rahulraghuwanshi.mystartheros.di.characters_list

import com.rahulraghuwanshi.mystartheros.data.local.StarWarDatabase
import com.rahulraghuwanshi.mystartheros.domain.use_case.FetchCharactersListUseCase
import com.rahulraghuwanshi.mystartheros.presentation.characters_list.CharactersViewModelFactory
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