package com.scallop.muviss.data.mappers

import com.scallop.muviss.data.entitites.ResultWrapperData
import com.scallop.muviss.data.entitites.TvShowDetailData
import com.scallop.muviss.data.entitites.TvShowItemData
import com.scallop.muviss.data.entitites.TvShowSeasonData
import com.scallop.muviss.domain.entities.ResultWrapperEntity
import com.scallop.muviss.domain.entities.TvShowDetailEntity
import com.scallop.muviss.domain.entities.TvShowItemEntity
import com.scallop.muviss.domain.entities.TvShowSeasonEntity

class DataEntityMapper constructor() {

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
        name = data.name,
        posterPath = data.posterPath,
        voteAverage = data.voteAverage,
        overview = data.overview
    )

    fun mapTvShowDetailResult(data: ResultWrapperData.Success<TvShowDetailData>) =
        ResultWrapperEntity.Success(
            mapTvShowDetail(data.value)
        )

    private fun mapTvShowDetail(data: TvShowDetailData) = TvShowDetailEntity(
        id = data.id,
        name = data.name,
        posterPath = data.posterPath,
        overview = data.overview,
        numberOfSeasons = data.numberOfSeasons,
        status = data.status,
        seasons = mapSeasons(data.seasons),
    )

    private fun mapSeasons(seasons: List<TvShowSeasonData>?) = seasons?.map { mapSeason(it) }

    private fun mapSeason(data: TvShowSeasonData) = TvShowSeasonEntity(
        id = data.id,
        name = data.name,
        posterPath = data.posterPath,
        overview = data.overview,
        airDate = data.airDate,
        seasonNumber = data.seasonNumber,
        episodeCount = data.episodeCount,
    )
}
