package com.scallop.muviss.data.entitites

import com.squareup.moshi.Json

data class TvShowDetailData(
    @Json(name = "id") var id: Long?,
    @Json(name = "title") var title: String?,
    @Json(name = "poster_path") var posterPath: String?,
    @Json(name = "overview") var overview: String?
)
