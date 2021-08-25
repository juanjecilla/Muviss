package com.scallop.muviss.data.repository

import com.scallop.muviss.data.api.TheMovieDbApi
import com.scallop.muviss.data.entitites.ResultWrapperData
import com.scallop.muviss.data.entitites.TvShowDetailData
import com.scallop.muviss.data.entitites.TvShowItemData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class RemoteDataSourceImpl(private val api: TheMovieDbApi) : RemoteDataSource {

    override suspend fun getTopRatedTvShows(
        page: Int
    ): Flow<ResultWrapperData<List<TvShowItemData>>> {
        return flow {
            try {
                val results = api.getTopRatedTvShows(page)
                emit(ResultWrapperData.Success(results.results))
            } catch (exception: HttpException) {
                emit(ResultWrapperData.GenericError(exception.code(), exception))
            } catch (exception: Exception) {
                emit(ResultWrapperData.GenericError(exception = exception))
            }
        }
    }

    override suspend fun getTvShowDetails(id: Long): Flow<ResultWrapperData<TvShowDetailData>> {
        return flow {
            try {
                val results = api.getTvShowDetails(id)
                emit(ResultWrapperData.Success(results))
            } catch (exception: HttpException) {
                emit(ResultWrapperData.GenericError(exception.code(), exception))
            } catch (exception: Exception) {
                emit(ResultWrapperData.GenericError(exception = exception))
            }
        }
    }

    override suspend fun getSimilarTvShows(
        id: Long,
        page: Int
    ): Flow<ResultWrapperData<List<TvShowItemData>>> {
        return flow {
            try {
                val results = api.getSimilarTvShows(id, page)
                emit(ResultWrapperData.Success(results.results))
            } catch (exception: HttpException) {
                emit(ResultWrapperData.GenericError(exception.code(), exception))
            } catch (exception: Exception) {
                emit(ResultWrapperData.GenericError(exception = exception))
            }
        }
    }
}