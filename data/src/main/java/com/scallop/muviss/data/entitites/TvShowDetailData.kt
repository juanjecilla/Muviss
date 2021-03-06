package com.scallop.muviss.data.entitites

import com.squareup.moshi.Json

data class TvShowDetailData(
    @Json(name = "id") var id: Long?,
    @Json(name = "name") var name: String?,
    @Json(name = "poster_path") var posterPath: String?,
    @Json(name = "overview") var overview: String?,
    @Json(name = "number_of_seasons") var numberOfSeasons: Int?,
    @Json(name = "status") var status: String?,
    @Json(name = "seasons") var seasons: List<TvShowSeasonData>?,
)
