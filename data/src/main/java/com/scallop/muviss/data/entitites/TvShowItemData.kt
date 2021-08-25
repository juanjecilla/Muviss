package com.scallop.muviss.data.entitites

import com.squareup.moshi.Json

data class TvShowItemData(
    @Json(name = "id") var id: Long?,
    @Json(name = "title") var title: String?,
    @Json(name = "poster_path") var posterPath: String?
)
