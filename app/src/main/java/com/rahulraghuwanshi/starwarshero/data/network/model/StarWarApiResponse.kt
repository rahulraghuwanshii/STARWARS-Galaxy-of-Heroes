package com.rahulraghuwanshi.starwarshero.data.network.model

import com.google.gson.annotations.SerializedName
import com.rahulraghuwanshi.starwarshero.domain.model.Character

data class StarWarApiResponse(
    @SerializedName("count")
    val count: Int? = null,
    @SerializedName("next")
    val next: String? = null,
    @SerializedName("previous")
    val previous: String? = null,
    @SerializedName("results")
    val characterList: List<Character>
)