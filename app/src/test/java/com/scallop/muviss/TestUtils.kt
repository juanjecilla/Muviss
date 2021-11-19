package com.scallop.muviss

import com.scallop.muviss.domain.entities.TvShowItemEntity

object TestUtils {

    fun getTvShows(size: Int): List<TvShowItemEntity> {
        val tvShows = mutableListOf<TvShowItemEntity>()

        for (i in 0 until size) {
            tvShows.add(getTvShow(i))
        }
        return tvShows
    }

    private fun getTvShow(i: Int) = TvShowItemEntity(
        id = i.toLong(),
        name = "Title $i",
        posterPath = "poster $i",
        voteAverage = i.toDouble(),
        overview = "Overview $i"
    )
}
