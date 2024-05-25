package com.rahulraghuwanshi.starwarshero.data.network.model

import com.google.gson.annotations.SerializedName

data class HomeWorldResponse(
    @SerializedName("name")
    val name: String
)