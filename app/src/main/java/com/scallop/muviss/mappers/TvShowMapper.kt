package com.scallop.muviss.mappers

import com.scallop.muviss.domain.entities.ResultWrapperEntity
import com.scallop.muviss.domain.entities.TvShowDetailEntity
import com.scallop.muviss.domain.entities.TvShowItemEntity
import com.scallop.muviss.domain.entities.TvShowSeasonEntity
import com.scallop.muviss.entities.TvShowDetail
import com.scallop.muviss.entities.TvShowItem
import com.scallop.muviss.entities.TvShowSeason

class TvShowMapper {

    fun mapResults(entity: ResultWrapperEntity.Success<List<TvShowItemEntity>>) =
        ResultWrapperEntity.Success(
            this.mapTvShowItems(entity.value)
        )

    fun mapResult(entity: ResultWrapperEntity.Success<TvShowItemEntity>) =
        ResultWrapperEntity.Success(
            mapTvShowItems(entity.value)
        )

    fun mapException(entity: ResultWrapperEntity.GenericError) = ResultWrapperEntity.GenericError(
        entity.code, entity.exception
    )

    fun mapTvShowItems(results: List<TvShowItemEntity>) =
        results.map { this.mapTvShowItems(it) }

    private fun mapTvShowItems(entity: TvShowItemEntity) = TvShowItem(
        id = entity.id,
        name = entity.name,
        posterPath = entity.posterPath,
        voteAverage = entity.voteAverage,
        overview = entity.overview
    )

    fun mapTvShowDetailResult(entity: ResultWrapperEntity.Success<TvShowDetailEntity>) =
        ResultWrapperEntity.Success(
            mapTvShowDetail(entity.value)
        )

    private fun mapTvShowDetail(entity: TvShowDetailEntity) = TvShowDetail(
        id = entity.id,
        name = entity.name,
        posterPath = entity.posterPath,
        overview = entity.overview,
        numberOfSeasons = entity.numberOfSeasons,
        status = entity.status,
        seasons = mapSeasons(entity.seasons),
    )

    private fun mapSeasons(seasons: List<TvShowSeasonEntity>?) = seasons?.map { mapSeason(it) }

    private fun mapSeason(entity: TvShowSeasonEntity) = TvShowSeason(
        id = entity.id,
        name = entity.name,
        posterPath = entity.posterPath,
        overview = entity.overview,
        airDate = entity.airDate,
        seasonNumber = entity.seasonNumber,
        episodeCount = entity.episodeCount,
    )
}
