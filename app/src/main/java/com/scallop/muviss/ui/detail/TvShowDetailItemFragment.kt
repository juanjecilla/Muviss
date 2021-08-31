package com.scallop.muviss.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.scallop.muviss.R
import com.scallop.muviss.databinding.FragmentTvShowDetailItemBinding
import com.scallop.muviss.entities.TvShowDetail
import com.scallop.muviss.ui.commons.viewBinding

class TvShowDetailItemFragment : Fragment() {

    private val binding by viewBinding<FragmentTvShowDetailItemBinding>()

    private lateinit var tvShow: TvShowDetail
    private lateinit var adapter: TvShowSeasonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getParcelable<TvShowDetail>(ARG_TV_SHOW)?.let {
            tvShow = it

            adapter = TvShowSeasonAdapter {

            }
            it.seasons?.let { seasons ->
                adapter.submitList(seasons.sortedBy { season -> season.seasonNumber })
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)

        return inflater.inflate(R.layout.fragment_tv_show_detail_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            tvShowDetailTitle.text = tvShow.name
            tvShowDetailImage.load("https://image.tmdb.org/t/p/w500/${tvShow.posterPath}")
            tvShowDetailOverview.text = tvShow.overview
            statusLabel.text = tvShow.status

            tvShow.numberOfSeasons?.let {
                val seasonsText = if (it > 1) {
                    getString(R.string.seasons_label)
                } else {
                    getString(R.string.season_label)
                }
                seasonsLabel.text = seasonsText.format(it)
            }

            seasonsList.adapter = adapter
            seasonsList.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    companion object {
        private const val ARG_TV_SHOW = "ARG_TV_SHOW"

        fun newInstance(card: TvShowDetail): TvShowDetailItemFragment {
            val fragment = TvShowDetailItemFragment()

            val args = Bundle()
            args.putParcelable(ARG_TV_SHOW, card)
            fragment.arguments = args

            return fragment
        }
    }
}