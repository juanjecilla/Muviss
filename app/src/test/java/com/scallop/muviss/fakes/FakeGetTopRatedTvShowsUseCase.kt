package com.scallop.muviss.fakes

import com.scallop.muviss.TestUtils
import com.scallop.muviss.domain.entities.ResultWrapperEntity
import com.scallop.muviss.domain.entities.TvShowItemEntity
import com.scallop.muviss.domain.usecases.GetTopRatedTvShowsBaseUseCase
import com.scallop.muviss.domain.usecases.GetTopRatedTvShowsUseCase
import com.scallop.muviss.utils.Status
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.IllegalStateException

class FakeGetTopRatedTvShowsUseCase(private val status: Status) :
    GetTopRatedTvShowsBaseUseCase {

    private fun execute(params: GetTopRatedTvShowsUseCase.Params): Flow<ResultWrapperEntity<List<TvShowItemEntity>>> =
        flow {
            when (status) {
                Status.SUCCESSFUL -> emit(ResultWrapperEntity.Success(TestUtils.getTvShows(params.page * 20)))
                else -> throw IllegalStateException("Something went wrong")
            }
        }

    override suspend fun invoke(params: GetTopRatedTvShowsUseCase.Params):
        Flow<ResultWrapperEntity<List<TvShowItemEntity>>> {
        return execute(params)
    }
}
