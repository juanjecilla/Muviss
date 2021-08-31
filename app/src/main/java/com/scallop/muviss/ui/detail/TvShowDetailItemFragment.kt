package com.scallop.muviss.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.scallop.muviss.R
import com.scallop.muviss.databinding.FragmentTvShowDetailItemBinding
import com.scallop.muviss.entities.TvShowDetail
import com.scallop.muviss.ui.commons.viewBinding

class TvShowDetailItemFragment : Fragment() {

    private val binding by viewBinding<FragmentTvShowDetailItemBinding>()

    private lateinit var tvShow: TvShowDetail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getParcelable<TvShowDetail>(ARG_TV_SHOW)?.let {
            tvShow = it
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