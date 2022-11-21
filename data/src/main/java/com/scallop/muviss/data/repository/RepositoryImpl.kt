package com.scallop.muviss.data.repository

import com.scallop.muviss.data.entitites.ResultWrapperData
import com.scallop.muviss.data.entitites.TvShowDetailData
import com.scallop.muviss.data.entitites.TvShowItemData
import com.scallop.muviss.data.mappers.DataEntityMapper
import com.scallop.muviss.domain.entities.ResultWrapperEntity
import com.scallop.muviss.domain.entities.TvShowDetailEntity
import com.scallop.muviss.domain.entities.TvShowItemEntity
import com.scallop.muviss.domain.repositories.TheMovieDbRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RepositoryImpl(
    private val remote: RemoteDataSource,
    private val mapper: DataEntityMapper,
    private val filter: TvShowFilter
) : TheMovieDbRepository {

    override suspend fun getTopRatedTvShows(
        page: Int,
    ): Flow<ResultWrapperEntity<List<TvShowItemEntity>>> {
        return remote.getTopRatedTvShows(page).map {
            when (it) {
                is ResultWrapperData.Success<*> -> mapper.mapResults(
                    it as ResultWrapperData.Success<List<TvShowItemData>>
                )
                is ResultWrapperData.GenericError -> mapper.mapException(it)
            }
        }
    }

    override suspend fun getTvShowDetails(id: Long): Flow<ResultWrapperEntity<TvShowDetailEntity>> {
        return remote.getTvShowDetails(id).map {
            when (it) {
                is ResultWrapperData.Success<*> -> mapper.mapTvShowDetailResult(
                    it as ResultWrapperData.Success<TvShowDetailData>
                )
                is ResultWrapperData.GenericError -> mapper.mapException(it)
            }
        }
    }

    override suspend fun getSimilarRatedTvShows(
        id: Long,
        page: Int
    ): Flow<ResultWrapperEntity<List<TvShowItemEntity>>> {
        return remote.getSimilarTvShows(id, page).map {
            when (it) {
                is ResultWrapperData.Success<*> ->
                    mapper.mapResults(it as ResultWrapperData.Success<List<TvShowItemData>>)
                is ResultWrapperData.GenericError -> mapper.mapException(it)
            }
        }
    }
}
