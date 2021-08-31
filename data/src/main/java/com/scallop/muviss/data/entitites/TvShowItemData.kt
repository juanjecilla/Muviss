package com.scallop.muviss.data.entitites

import com.squareup.moshi.Json

data class TvShowItemData(
    @Json(name = "id") var id: Long?,
    @Json(name = "name") var name: String?,
    @Json(name = "poster_path") var posterPath: String?,
    @Json(name = "vote_average") var voteAverage: Double?,
    @Json(name = "overview") var overview: String?,
)
