package com.scallop.muviss.data.repository

import com.scallop.muviss.data.entitites.TvShowItemData
import com.scallop.muviss.data.repository.rules.FilterRule
import com.scallop.muviss.domain.entities.TvShowItemEntity
import java.util.*

class TVShowFIlterImpl : TvShowFilter {

    private val filters = mutableListOf<FilterRule>()


    override fun filter(tvShowList: List<TvShowItemData>, timestamp: Long): List<TvShowItemData> {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timestamp

        val rule = object : FilterRule {
            override fun valid(): Boolean {
                return calendar[Calendar.DAY_OF_WEEK] == Calendar.MONDAY
            }

            override fun filter(input: List<TvShowItemData>): List<TvShowItemData> {
                return input.filter { it.voteAverage!! > 3}
            }
        }

        filters.add(rule)

        val expectedRule = filters.firstOrNull { it.valid() }

        return when(calendar[Calendar.DAY_OF_WEEK]){
            Calendar.MONDAY -> {
                tvShowList.filter { it.voteAverage!! > 3}
            }
            Calendar.TUESDAY -> {
                tvShowList.filter { it.name!!.lowercase().startsWith("a")}

            }
            Calendar.WEDNESDAY -> {
                tvShowList.filter { it.id!! % 2 == 0L}
            }
            else -> tvShowList
        }
    }
}
