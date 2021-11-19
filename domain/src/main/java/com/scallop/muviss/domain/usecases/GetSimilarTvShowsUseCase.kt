package com.scallop.muviss.domain.usecases

import com.scallop.muviss.domain.common.BaseUseCase
import com.scallop.muviss.domain.entities.ResultWrapperEntity
import com.scallop.muviss.domain.entities.TvShowItemEntity
import com.scallop.muviss.domain.repositories.TheMovieDbRepository
import kotlinx.coroutines.flow.Flow

typealias GetSimilarTvShowsBaseUseCase = BaseUseCase<GetSimilarTvShowsUseCase.Params, Flow<@JvmSuppressWildcards ResultWrapperEntity<List<TvShowItemEntity>>>>

class GetSimilarTvShowsUseCase(
    private val repository: TheMovieDbRepository
) : GetSimilarTvShowsBaseUseCase {

    override suspend fun invoke(params: Params) =
        repository.getSimilarRatedTvShows(params.id, params.page)

    data class Params(
        val id: Long,
        val page: Int
    )
}
