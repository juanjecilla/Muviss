package com.scallop.muviss.data.repository

import com.scallop.muviss.data.entitites.TvShowItemData
import com.scallop.muviss.domain.entities.TvShowItemEntity

interface TvShowFilter {
    fun filter(tvShowList: List<TvShowItemData>, timestamp: Long): List<TvShowItemData>
}
