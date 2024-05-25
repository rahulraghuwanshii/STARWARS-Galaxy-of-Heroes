package com.rahulraghuwanshi.starwarshero.data.repository.dataSource

import com.rahulraghuwanshi.starwarshero.data.network.model.HomeWorldResponse
import com.rahulraghuwanshi.starwarshero.data.network.model.MoviesResponse
import com.rahulraghuwanshi.starwarshero.data.network.model.StarWarApiResponse
import com.rahulraghuwanshi.starwarshero.util.RestClientResult

interface StarWarRemoteDataSource {
    suspend fun fetchCharacters(page: Int): RestClientResult<StarWarApiResponse?>

    suspend fun fetchMovies(url: String): RestClientResult<MoviesResponse>

    suspend fun fetchHomeWorld(url: String): RestClientResult<HomeWorldResponse>
}