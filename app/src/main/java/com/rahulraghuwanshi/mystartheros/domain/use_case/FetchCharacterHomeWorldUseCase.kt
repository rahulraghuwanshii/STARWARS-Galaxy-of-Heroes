package com.rahulraghuwanshi.mystartheros.domain.use_case

import com.rahulraghuwanshi.mystartheros.data.network.model.HomeWorldResponse
import com.rahulraghuwanshi.mystartheros.util.RestClientResult
import kotlinx.coroutines.flow.Flow

interface FetchCharacterHomeWorldUseCase {

    suspend fun fetchHomeWorld(url: String): Flow<RestClientResult<HomeWorldResponse>>
}