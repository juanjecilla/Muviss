package com.scallop.muviss.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.scallop.muviss.R
import com.scallop.muviss.databinding.ItemTvShowBinding
import com.scallop.muviss.entities.TvShowItem

class TvShowAdapter(private val onClick: (TvShowItem) -> (Unit)) :
    ListAdapter<TvShowItem, TvShowAdapter.TvShowListViewHolder>(TvShowDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_tv_show, parent, false)
        return TvShowListViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvShowListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TvShowListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private lateinit var item: TvShowItem
        private val binding = ItemTvShowBinding.bind(itemView)

        init {
            itemView.setOnClickListener { onClick(item) }
        }

        fun bind(item: TvShowItem) {
            this.item = item
            with(binding) {
                tvShowTitle.text = item.name
                tvShowImage.load("https://image.tmdb.org/t/p/w500/${item.posterPath}")
                tvShowRating.text = item.voteAverage.toString()
            }
        }
    }

    class TvShowDiffCallback : DiffUtil.ItemCallback<TvShowItem>() {

        override fun areItemsTheSame(oldItem: TvShowItem, newItem: TvShowItem) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: TvShowItem, newItem: TvShowItem) =
            oldItem == newItem
    }
}