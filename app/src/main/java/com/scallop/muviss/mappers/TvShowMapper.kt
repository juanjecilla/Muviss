package com.scallop.muviss.mappers

import com.scallop.muviss.domain.entities.ResultWrapperEntity
import com.scallop.muviss.domain.entities.TvShowDetailEntity
import com.scallop.muviss.domain.entities.TvShowItemEntity
import com.scallop.muviss.entities.TvShowDetail
import com.scallop.muviss.entities.TvShowItem

class TvShowMapper {

    fun mapResults(data: ResultWrapperEntity.Success<List<TvShowItemEntity>>) =
        ResultWrapperEntity.Success(
            this.mapTvShowItems(data.value)
        )

    fun mapResult(data: ResultWrapperEntity.Success<TvShowItemEntity>) =
        ResultWrapperEntity.Success(
            mapTvShowItems(data.value)
        )

    fun mapException(data: ResultWrapperEntity.GenericError) = ResultWrapperEntity.GenericError(
        data.code, data.exception
    )

    fun mapTvShowItems(results: List<TvShowItemEntity>) =
        results.map { this.mapTvShowItems(it) }

    private fun mapTvShowItems(data: TvShowItemEntity) = TvShowItem(
        id = data.id,
        name = data.name,
        posterPath = data.posterPath,
        voteAverage = data.voteAverage
    )

    fun mapTvShowDetailResult(data: ResultWrapperEntity.Success<TvShowDetailEntity>) =
        ResultWrapperEntity.Success(
            mapTvShowDetail(data.value)
        )

    private fun mapTvShowDetail(data: TvShowDetailEntity) = TvShowDetail(
        id = data.id,
        name = data.name,
        posterPath = data.posterPath,
        overview = data.overview
    )
}
