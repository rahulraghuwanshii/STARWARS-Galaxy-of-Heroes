package com.rahulraghuwanshi.mystartheros.data.network.model

import com.google.gson.annotations.SerializedName

data class HomeWorldResponse(
    @SerializedName("name")
    val name: String
)