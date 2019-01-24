package com.ven10.example.api

import com.google.gson.annotations.SerializedName
import com.ven10.example.model.GitRepo

/**
 * Data class to hold repo responses from searchRepo API calls.
 */
data class SearchResponse(
    @SerializedName("total_count") val total: Int = 0,
    @SerializedName("items") val items: List<GitRepo> = emptyList(),
    val nextPage: Int? = null
)
