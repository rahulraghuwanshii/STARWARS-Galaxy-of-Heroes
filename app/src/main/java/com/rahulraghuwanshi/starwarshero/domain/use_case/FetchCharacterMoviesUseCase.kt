package com.rahulraghuwanshi.starwarshero.domain.use_case

import com.rahulraghuwanshi.starwarshero.data.network.model.MoviesResponse
import com.rahulraghuwanshi.starwarshero.util.RestClientResult
import kotlinx.coroutines.flow.Flow

interface FetchCharacterMoviesUseCase {

    suspend fun fetchMovies(url: String): Flow<RestClientResult<MoviesResponse>>
}