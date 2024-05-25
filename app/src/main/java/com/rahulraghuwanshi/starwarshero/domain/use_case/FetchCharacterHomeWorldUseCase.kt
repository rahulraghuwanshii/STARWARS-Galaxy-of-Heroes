package com.rahulraghuwanshi.starwarshero.domain.use_case

import com.rahulraghuwanshi.starwarshero.data.network.model.HomeWorldResponse
import com.rahulraghuwanshi.starwarshero.util.RestClientResult
import kotlinx.coroutines.flow.Flow

interface FetchCharacterHomeWorldUseCase {

    suspend fun fetchHomeWorld(url: String): Flow<RestClientResult<HomeWorldResponse>>
}