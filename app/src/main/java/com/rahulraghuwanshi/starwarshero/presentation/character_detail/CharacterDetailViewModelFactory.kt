package com.rahulraghuwanshi.starwarshero.presentation.character_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rahulraghuwanshi.starwarshero.domain.use_case.FetchCharacterHomeWorldUseCase
import com.rahulraghuwanshi.starwarshero.domain.use_case.FetchCharacterMoviesUseCase

class CharacterDetailViewModelFactory(
    private val fetchCharacterHomeWorldUseCase: FetchCharacterHomeWorldUseCase,
    private val fetchCharacterMoviesUseCase: FetchCharacterMoviesUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CharacterDetailViewModel(
            fetchCharacterHomeWorldUseCase,
            fetchCharacterMoviesUseCase
        ) as T
    }
}