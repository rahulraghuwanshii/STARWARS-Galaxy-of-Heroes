package com.rahulraghuwanshi.starwarshero.presentation.characters_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rahulraghuwanshi.starwarshero.data.local.StarWarDatabase
import com.rahulraghuwanshi.starwarshero.domain.use_case.FetchCharactersListUseCase

class CharactersViewModelFactory(
    private val fetchCharactersListUseCase: FetchCharactersListUseCase,
    private val starWarDatabase: StarWarDatabase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CharactersViewModel(
            fetchCharactersListUseCase = fetchCharactersListUseCase,
            starWarDatabase = starWarDatabase
        ) as T
    }
}