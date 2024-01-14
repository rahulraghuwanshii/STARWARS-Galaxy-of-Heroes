package com.rahulraghuwanshi.mystartheros.data.network

import com.rahulraghuwanshi.mystartheros.data.network.model.HomeWorldResponse
import com.rahulraghuwanshi.mystartheros.data.network.model.MoviesResponse
import com.rahulraghuwanshi.mystartheros.data.network.model.StarWarApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface StarWarApiInterface {

    @GET("people/")
    suspend fun fetchCharacters(@Query("page") page: Int): Response<StarWarApiResponse?>

    @GET
    suspend fun fetchMovies(@Url url: String): Response<MoviesResponse>

    @GET
    suspend fun fetchHomeWorld(@Url url: String): Response<HomeWorldResponse>
}