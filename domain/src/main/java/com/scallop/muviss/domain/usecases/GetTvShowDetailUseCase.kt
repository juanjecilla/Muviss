package com.scallop.muviss.domain.usecases

import com.scallop.muviss.domain.common.BaseUseCase
import com.scallop.muviss.domain.entities.ResultWrapperEntity
import com.scallop.muviss.domain.entities.TvShowDetailEntity
import com.scallop.muviss.domain.repositories.TheMovieDbRepository
import kotlinx.coroutines.flow.Flow

typealias GetTvShowDetailsBaseUseCase = BaseUseCase<GetTvShowDetailsUseCase.Params, Flow<ResultWrapperEntity<TvShowDetailEntity>>>

class GetTvShowDetailsUseCase(
    private val mRepository: TheMovieDbRepository
) : GetTvShowDetailsBaseUseCase {

    override suspend fun invoke(params: Params) =
        mRepository.getTvShowDetails(params.id)

    data class Params(
        val id: Long
    )
}