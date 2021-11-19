package com.scallop.muviss.ui.list

import com.scallop.muviss.entities.TvShowItem

sealed class TvShowListState {
    data class TvShowListItems(val items: List<TvShowItem>) : TvShowListState()
    data class TvShowListFailure(val failure: String) : TvShowListState()
    data class TvShowListLoading(val show: Boolean) : TvShowListState()
}
