package com.rahulraghuwanshi.mystartheros.domain.use_case.impl

import com.rahulraghuwanshi.mystartheros.domain.repository.StarWarRepository
import com.rahulraghuwanshi.mystartheros.domain.use_case.FetchCharactersListUseCase

class FetchCharactersListUseCaseImpl(
    private val starWarRepository: StarWarRepository
) : FetchCharactersListUseCase {
    override suspend fun fetchCharacters(page: Int) = starWarRepository.fetchCharacters(page)
}