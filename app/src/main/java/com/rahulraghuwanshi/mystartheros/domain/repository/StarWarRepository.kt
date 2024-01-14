package com.rahulraghuwanshi.mystartheros.domain.repository

import com.rahulraghuwanshi.mystartheros.data.network.model.HomeWorldResponse
import com.rahulraghuwanshi.mystartheros.data.network.model.MoviesResponse
import com.rahulraghuwanshi.mystartheros.data.network.model.StarWarApiResponse
import com.rahulraghuwanshi.mystartheros.util.RestClientResult
import kotlinx.coroutines.flow.Flow

interface StarWarRepository {

    suspend fun fetchCharacters(page: Int): RestClientResult<StarWarApiResponse?>

    suspend fun fetchMovies(url: String): Flow<RestClientResult<MoviesResponse>>

    suspend fun fetchHomeWorld(url: String): Flow<RestClientResult<HomeWorldResponse>>
}