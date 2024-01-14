package com.rahulraghuwanshi.mystartheros.domain.use_case

import com.rahulraghuwanshi.mystartheros.data.network.model.MoviesResponse
import com.rahulraghuwanshi.mystartheros.util.RestClientResult
import kotlinx.coroutines.flow.Flow

interface FetchCharacterMoviesUseCase {

    suspend fun fetchMovies(url: String): Flow<RestClientResult<MoviesResponse>>
}