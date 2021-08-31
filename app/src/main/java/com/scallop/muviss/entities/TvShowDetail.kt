package com.scallop.muviss.entities

data class TvShowDetail(
    var id: Long?,
    var name: String?,
    var posterPath: String?,
    var overview: String?,
    var numberOfSeasons: Int?,
    var status: String?,
    var seasons: List<TvShowSeason>?,
) : TvShow()
