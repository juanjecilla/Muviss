package com.scallop.muviss.domain.entities

data class TvShowItemEntity(
    var id: Long?,
    var name: String?,
    var posterPath: String?,
    var voteAverage: Double?,
    var overview: String?,
)

// If today is Monday -> Return entities with voteAverage > 3,
// Tuesday -> name starts with A
// Wednesday -> id%2 == 0
// Else ->
