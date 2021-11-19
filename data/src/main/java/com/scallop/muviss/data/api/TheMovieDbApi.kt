package com.scallop.muviss.data.api

import com.scallop.muviss.data.entitites.PagedResultData
import com.scallop.muviss.data.entitites.TvShowDetailData
import com.scallop.muviss.data.entitites.TvShowItemData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDbApi {

    @GET("/3/tv/top_rated")
    suspend fun getTopRatedTvShows(@Query("page") page: Int): PagedResultData<TvShowItemData>

    @GET("/3/tv/{tv_id}")
    suspend fun getTvShowDetails(@Path("tv_id") tvId: Long): TvShowDetailData

    @GET("/3/tv/{tv_id}/similar")
    suspend fun getSimilarTvShows(
        @Path("tv_id") tvId: Long,
        @Query("page") page: Int
    ): PagedResultData<TvShowItemData>
}
