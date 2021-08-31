package com.scallop.muviss.ui.detail

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.scallop.muviss.entities.TvShow
import com.scallop.muviss.entities.TvShowDetail
import com.scallop.muviss.entities.TvShowItem

/**
 * Created by juanje on 10/04/18.
 */
class TvShowDetailPagerAdapter(
    private val data: MutableList<TvShow> = mutableListOf(),
    fm: FragmentManager,
    lifecycle: Lifecycle
) :
    FragmentStateAdapter(fm, lifecycle) {
    override fun getItemCount(): Int {
        return data.size
    }

    override fun createFragment(position: Int): Fragment {
        return when (val item = data[position]) {
            is TvShowDetail -> TvShowDetailItemFragment.newInstance(item)
            is TvShowItem -> TvShowDetailSimilarFragment.newInstance(item)
            else -> throw IllegalArgumentException()
        }
    }

    fun addItems(items: List<TvShow>) {
        Log.d("HOLA", "${items.size} ${data.size}")
        val prevSize = data.size
        data.addAll(items)
        notifyItemRangeInserted(prevSize, items.size)
    }
}