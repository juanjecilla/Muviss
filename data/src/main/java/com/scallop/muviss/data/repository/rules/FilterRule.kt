package com.scallop.muviss.data.repository.rules

import com.scallop.muviss.data.entitites.TvShowItemData

interface FilterRule {
    fun valid(): Boolean
    fun filter(input: List<TvShowItemData>): List<TvShowItemData>
}
