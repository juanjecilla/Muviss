package com.scallop.muviss.data.entitites

import com.squareup.moshi.Json

data class TvShowSeasonData(
    @Json(name = "id") var id: Long?,
    @Json(name = "name") var name: String?,
    @Json(name = "poster_path") var posterPath: String?,
    @Json(name = "overview") var overview: String?,
    @Json(name = "air_date") var airDate: String?,
    @Json(name = "season_number") var seasonNumber: Int?,
    @Json(name = "episode_count") var episodeCount: Int?
)
