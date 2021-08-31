package com.scallop.muviss.data.repository

import com.scallop.muviss.data.entitites.ResultWrapperData
import com.scallop.muviss.data.entitites.TvShowDetailData
import com.scallop.muviss.data.entitites.TvShowItemData
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    suspend fun getTopRatedTvShows(
        page: Int,
    ): Flow<ResultWrapperData<List<TvShowItemData>>>

    suspend fun getTvShowDetails(id: Long): Flow<ResultWrapperData<TvShowDetailData>>

    suspend fun getSimilarTvShows(
        id: Long,
        page: Int
    ): Flow<ResultWrapperData<List<TvShowItemData>>>
}