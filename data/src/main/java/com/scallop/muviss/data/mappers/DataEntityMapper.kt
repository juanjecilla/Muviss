package com.scallop.muviss.data.mappers

import com.scallop.muviss.data.entitites.ResultWrapperData
import com.scallop.muviss.data.entitites.TvShowDetailData
import com.scallop.muviss.data.entitites.TvShowItemData
import com.scallop.muviss.domain.entities.ResultWrapperEntity
import com.scallop.muviss.domain.entities.TvShowDetailEntity
import com.scallop.muviss.domain.entities.TvShowItemEntity

class DataEntityMapper {

    fun mapResults(data: ResultWrapperData.Success<List<TvShowItemData>>) =
        ResultWrapperEntity.Success(
            this.mapTvShowItem(data.value)
        )

    fun mapResult(data: ResultWrapperData.Success<TvShowItemData>) =
        ResultWrapperEntity.Success(
            mapTvShowItem(data.value)
        )

    fun mapException(data: ResultWrapperData.GenericError) = ResultWrapperEntity.GenericError(
        data.code, data.exception
    )

    private fun mapTvShowItem(results: List<TvShowItemData>) =
        results.map { this.mapTvShowItem(it) }

    private fun mapTvShowItem(data: TvShowItemData) = TvShowItemEntity(
        id = data.id,
        title = data.title,
        posterPath = data.posterPath
    )

    fun mapTvShowDetailResult(data: ResultWrapperData.Success<TvShowDetailData>) =
        ResultWrapperEntity.Success(
            mapTvShowDetail(data.value)
        )

    private fun mapTvShowDetail(data: TvShowDetailData) = TvShowDetailEntity(
        id = data.id,
        title = data.title,
        posterPath = data.posterPath,
        overview = data.overview
    )
}
