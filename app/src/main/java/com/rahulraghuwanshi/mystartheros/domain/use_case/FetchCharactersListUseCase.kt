package com.rahulraghuwanshi.mystartheros.domain.use_case

import com.rahulraghuwanshi.mystartheros.data.network.model.StarWarApiResponse
import com.rahulraghuwanshi.mystartheros.util.RestClientResult

interface FetchCharactersListUseCase {
    suspend fun fetchCharacters(page: Int): RestClientResult<StarWarApiResponse?>
}