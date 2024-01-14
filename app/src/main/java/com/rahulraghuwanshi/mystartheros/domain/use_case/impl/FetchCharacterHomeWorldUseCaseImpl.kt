package com.rahulraghuwanshi.mystartheros.domain.use_case.impl

import com.rahulraghuwanshi.mystartheros.domain.repository.StarWarRepository
import com.rahulraghuwanshi.mystartheros.domain.use_case.FetchCharacterHomeWorldUseCase

class FetchCharacterHomeWorldUseCaseImpl(
    private val starWarRepository: StarWarRepository
) : FetchCharacterHomeWorldUseCase {
    override suspend fun fetchHomeWorld(url: String) = starWarRepository.fetchHomeWorld(url)
}