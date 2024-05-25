package com.rahulraghuwanshi.starwarshero.domain.repository

import com.rahulraghuwanshi.starwarshero.data.network.model.HomeWorldResponse
import com.rahulraghuwanshi.starwarshero.data.network.model.MoviesResponse
import com.rahulraghuwanshi.starwarshero.data.network.model.StarWarApiResponse
import com.rahulraghuwanshi.starwarshero.util.RestClientResult
import kotlinx.coroutines.flow.Flow

interface StarWarRepository {

    suspend fun fetchCharacters(page: Int): RestClientResult<StarWarApiResponse?>

    suspend fun fetchMovies(url: String): Flow<RestClientResult<MoviesResponse>>

    suspend fun fetchHomeWorld(url: String): Flow<RestClientResult<HomeWorldResponse>>
}