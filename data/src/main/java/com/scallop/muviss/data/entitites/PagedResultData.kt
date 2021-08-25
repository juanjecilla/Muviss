package com.scallop.muviss.data.entitites

import com.squareup.moshi.Json

data class PagedResultData<T>(
    @Json(name = "page") var page: Int?,
    @Json(name = "total_results") var totalResults: Int?,
    @Json(name = "total_pages") var totalPages: Int?,
    @Json(name = "results") var results: List<T> = emptyList()
)
