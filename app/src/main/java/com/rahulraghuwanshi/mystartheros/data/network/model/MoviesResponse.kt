package com.rahulraghuwanshi.mystartheros.data.network.model

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName("opening_crawl")
    val openingCrawl: String,
    @SerializedName("title")
    val title: String
)