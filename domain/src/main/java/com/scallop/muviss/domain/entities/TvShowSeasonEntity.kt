package com.scallop.muviss.domain.entities

data class TvShowSeasonEntity(
    var id: Long?,
    var name: String?,
    var posterPath: String?,
    var overview: String?,
    var airDate: String?,
    var seasonNumber: Int?,
    var episodeCount: Int?
)
