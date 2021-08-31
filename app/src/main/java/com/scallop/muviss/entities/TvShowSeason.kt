package com.scallop.muviss.entities


data class TvShowSeason(
    var id: Long?,
    var name: String?,
    var posterPath: String?,
    var overview: String?,
    var airDate: String?,
    var seasonNumber: Int?,
    var episodeCount: Int?
)
