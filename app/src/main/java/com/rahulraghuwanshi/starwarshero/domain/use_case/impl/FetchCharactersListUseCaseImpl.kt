package com.rahulraghuwanshi.starwarshero.domain.use_case.impl

import com.rahulraghuwanshi.starwarshero.domain.repository.StarWarRepository
import com.rahulraghuwanshi.starwarshero.domain.use_case.FetchCharactersListUseCase

class FetchCharactersListUseCaseImpl(
    private val starWarRepository: StarWarRepository
) : FetchCharactersListUseCase {
    override suspend fun fetchCharacters(page: Int) = starWarRepository.fetchCharacters(page)
}