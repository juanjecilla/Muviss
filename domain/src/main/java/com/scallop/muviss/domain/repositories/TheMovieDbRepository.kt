package com.scallop.muviss.domain.repositories

import com.scallop.muviss.domain.entities.ResultWrapperEntity
import com.scallop.muviss.domain.entities.TvShowDetailEntity
import com.scallop.muviss.domain.entities.TvShowItemEntity
import kotlinx.coroutines.flow.Flow

interface TheMovieDbRepository {

    suspend fun getTopRatedTvShows(
        page: Int,
    ): Flow<ResultWrapperEntity<List<TvShowItemEntity>>>

    suspend fun getTvShowDetails(id: Long): Flow<ResultWrapperEntity<TvShowDetailEntity>>

    suspend fun getSimilarRatedTvShows(
        id: Long,
        page: Int
    ): Flow<ResultWrapperEntity<List<TvShowItemEntity>>>
}