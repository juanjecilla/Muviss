package com.scallop.muviss.domain.usecases

import com.scallop.muviss.domain.common.BaseUseCase
import com.scallop.muviss.domain.entities.ResultWrapperEntity
import com.scallop.muviss.domain.entities.TvShowItemEntity
import com.scallop.muviss.domain.repositories.TheMovieDbRepository
import kotlinx.coroutines.flow.Flow

typealias GetSimilarRatedTvShowsBaseUseCase = BaseUseCase<GetSimilarRatedTvShowsUseCase.Params, Flow<ResultWrapperEntity<List<TvShowItemEntity>>>>

class GetSimilarRatedTvShowsUseCase(
    private val mRepository: TheMovieDbRepository
) : GetSimilarRatedTvShowsBaseUseCase {

    override suspend fun invoke(params: Params) =
        mRepository.getSimilarRatedTvShows(params.id, params.page)

    data class Params(
        val id: Long,
        val page: Int
    )
}