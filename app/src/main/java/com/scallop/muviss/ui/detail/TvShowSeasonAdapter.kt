package com.scallop.muviss.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.scallop.muviss.R
import com.scallop.muviss.databinding.ItemTvShowSeasonBinding
import com.scallop.muviss.entities.TvShowSeason

class TvShowSeasonAdapter(private val onClick: (TvShowSeason) -> (Unit)) :
    ListAdapter<TvShowSeason, TvShowSeasonAdapter.TvShowListViewHolder>(TvShowDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_tv_show_season, parent, false)
        return TvShowListViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvShowListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TvShowListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private lateinit var item: TvShowSeason
        private val binding = ItemTvShowSeasonBinding.bind(itemView)

        init {
            itemView.setOnClickListener { onClick(item) }
        }

        fun bind(item: TvShowSeason) {
            this.item = item
            with(binding) {
                seasonName.text = item.name

                item.episodeCount?.let {
                    val seasonsText = if (it > 1) {
                        itemView.context.getString(R.string.episodes_label)
                    } else {
                        itemView.context.getString(R.string.episode_label)
                    }
                    seasonEpisodeCount.text = seasonsText.format(it)
                }
            }
        }
    }

    class TvShowDiffCallback : DiffUtil.ItemCallback<TvShowSeason>() {

        override fun areItemsTheSame(oldItem: TvShowSeason, newItem: TvShowSeason) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: TvShowSeason, newItem: TvShowSeason) =
            oldItem == newItem
    }
}
