package com.scallop.muviss.domain.usecases

import com.scallop.muviss.domain.common.BaseUseCase
import com.scallop.muviss.domain.entities.ResultWrapperEntity
import com.scallop.muviss.domain.entities.TvShowItemEntity
import com.scallop.muviss.domain.repositories.TheMovieDbRepository
import kotlinx.coroutines.flow.Flow

typealias GetTopRatedTvShowsBaseUseCase = BaseUseCase<GetTopRatedTvShowsUseCase.Params, Flow<@JvmSuppressWildcards ResultWrapperEntity<List<TvShowItemEntity>>>>

class GetTopRatedTvShowsUseCase(
    private val repository: TheMovieDbRepository
) : GetTopRatedTvShowsBaseUseCase {

    override suspend fun invoke(params: Params) =
        repository.getTopRatedTvShows(params.page)

    data class Params(
        val page: Int
    )
}