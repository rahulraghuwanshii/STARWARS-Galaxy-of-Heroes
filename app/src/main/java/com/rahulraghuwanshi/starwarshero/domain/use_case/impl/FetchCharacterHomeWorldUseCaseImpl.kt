package com.rahulraghuwanshi.starwarshero.domain.use_case.impl

import com.rahulraghuwanshi.starwarshero.domain.repository.StarWarRepository
import com.rahulraghuwanshi.starwarshero.domain.use_case.FetchCharacterHomeWorldUseCase

class FetchCharacterHomeWorldUseCaseImpl(
    private val starWarRepository: StarWarRepository
) : FetchCharacterHomeWorldUseCase {
    override suspend fun fetchHomeWorld(url: String) = starWarRepository.fetchHomeWorld(url)
}