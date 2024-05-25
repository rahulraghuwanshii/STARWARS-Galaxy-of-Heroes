package com.rahulraghuwanshi.starwarshero.data.repository

import com.rahulraghuwanshi.starwarshero.data.repository.dataSource.StarWarRemoteDataSource
import com.rahulraghuwanshi.starwarshero.domain.repository.StarWarRepository
import com.rahulraghuwanshi.starwarshero.util.RestClientResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class StarWarRepositoryImpl(
    private val startWarRemoteDataSource: StarWarRemoteDataSource
) : StarWarRepository {

    private suspend fun <T> getFlowResult(call: suspend () -> RestClientResult<T>): Flow<RestClientResult<T>> =
        flow {
            emit(RestClientResult.loading())
            try {
                val result = call.invoke()
                emit(result)
            } catch (e: Exception) {
                e.printStackTrace()
                emit(RestClientResult.error("Something went wrong with ${e.message}"))
            }
        }

    override suspend fun fetchCharacters(page: Int) = startWarRemoteDataSource.fetchCharacters(page)
    override suspend fun fetchMovies(url: String) = getFlowResult {
        startWarRemoteDataSource.fetchMovies(url)
    }

    override suspend fun fetchHomeWorld(url: String) = getFlowResult {
        startWarRemoteDataSource.fetchHomeWorld(url)
    }
}