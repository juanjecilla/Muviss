package com.scallop.muviss.ui.detail

import com.scallop.muviss.entities.TvShowDetail
import com.scallop.muviss.entities.TvShowItem

sealed class TvShowDetailState {
    data class TvShowDetailItems(val item: TvShowDetail) : TvShowDetailState()
    data class TvShowDetailRelatedItems(val items: List<TvShowItem>) : TvShowDetailState()
    data class TvShowDetailFailure(val failure: String) : TvShowDetailState()
    data class TvShowDetailLoading(val show: Boolean) : TvShowDetailState()
}
