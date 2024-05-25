package com.rahulraghuwanshi.starwarshero.domain.use_case

import com.rahulraghuwanshi.starwarshero.data.network.model.StarWarApiResponse
import com.rahulraghuwanshi.starwarshero.util.RestClientResult

interface FetchCharactersListUseCase {
    suspend fun fetchCharacters(page: Int): RestClientResult<StarWarApiResponse?>
}