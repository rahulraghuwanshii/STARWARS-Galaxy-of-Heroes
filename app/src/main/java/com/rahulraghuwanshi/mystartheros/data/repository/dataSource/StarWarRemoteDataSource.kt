package com.rahulraghuwanshi.mystartheros.data.repository.dataSource

import com.rahulraghuwanshi.mystartheros.data.network.model.HomeWorldResponse
import com.rahulraghuwanshi.mystartheros.data.network.model.MoviesResponse
import com.rahulraghuwanshi.mystartheros.data.network.model.StarWarApiResponse
import com.rahulraghuwanshi.mystartheros.util.RestClientResult

interface StarWarRemoteDataSource {
    suspend fun fetchCharacters(page: Int): RestClientResult<StarWarApiResponse?>

    suspend fun fetchMovies(url: String): RestClientResult<MoviesResponse>

    suspend fun fetchHomeWorld(url: String): RestClientResult<HomeWorldResponse>
}