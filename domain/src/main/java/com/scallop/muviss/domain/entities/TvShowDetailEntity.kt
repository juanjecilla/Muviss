package com.scallop.muviss.domain.entities

data class TvShowDetailEntity(
    var id: Long?,
    var name: String?,
    var posterPath: String?,
    var overview: String?,
    var numberOfSeasons: Int?,
    var status: String?,
    var seasons: List<TvShowSeasonEntity>?,
)
