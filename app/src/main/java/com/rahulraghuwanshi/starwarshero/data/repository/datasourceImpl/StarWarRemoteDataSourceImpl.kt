package com.rahulraghuwanshi.starwarshero.data.repository.datasourceImpl

import com.rahulraghuwanshi.starwarshero.data.network.StarWarApiInterface
import com.rahulraghuwanshi.starwarshero.data.repository.dataSource.StarWarRemoteDataSource
import com.rahulraghuwanshi.starwarshero.util.RestClientResult
import retrofit2.Response

class StarWarRemoteDataSourceImpl constructor(
    private val apiInterface: StarWarApiInterface
) : StarWarRemoteDataSource {

    private suspend fun <T> getResult(
        apiCall: suspend () -> Response<T>,
    ): RestClientResult<T> {
        return try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null)
                    RestClientResult.success(body)
                else
                    RestClientResult.error(response.message())
            } else
                RestClientResult.error(response.message())
        } catch (e: Exception) {
            RestClientResult.error(e.message ?: "Something went wrong")
        }
    }

    override suspend fun fetchCharacters(page: Int) = getResult {
        apiInterface.fetchCharacters(page = page)
    }

    override suspend fun fetchMovies(url: String) = getResult {
        apiInterface.fetchMovies(url = url)
    }

    override suspend fun fetchHomeWorld(url: String) = getResult {
        apiInterface.fetchHomeWorld(url = url)
    }
}