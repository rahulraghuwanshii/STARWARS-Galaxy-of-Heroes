package com.rahulraghuwanshi.starwarshero.domain.use_case.impl

import com.rahulraghuwanshi.starwarshero.domain.repository.StarWarRepository
import com.rahulraghuwanshi.starwarshero.domain.use_case.FetchCharacterMoviesUseCase

class FetchCharacterMoviesUseCaseImpl(
    private val starWarRepository: StarWarRepository
) : FetchCharacterMoviesUseCase {
    override suspend fun fetchMovies(url: String) = starWarRepository.fetchMovies(url)
}