package com.scallop.muviss.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.scallop.muviss.R
import com.scallop.muviss.databinding.FragmentTvShowDetailSimilarBinding
import com.scallop.muviss.entities.TvShowItem
import com.scallop.muviss.ui.commons.viewBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class TvShowDetailSimilarFragment : Fragment() {

    private val viewModel: TvShowDetailViewModel by sharedViewModel()
    private val binding by viewBinding<FragmentTvShowDetailSimilarBinding>()

    private lateinit var tvShow: TvShowItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getParcelable<TvShowItem>(ARG_TV_SHOW)?.let {
            tvShow = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)

        return inflater.inflate(R.layout.fragment_tv_show_detail_similar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            tvShowDetailTitle.text = tvShow.name
        }
    }

    companion object {
        private const val ARG_TV_SHOW = "ARG_TV_SHOW"

        fun newInstance(card: TvShowItem): TvShowDetailSimilarFragment {
            val fragment = TvShowDetailSimilarFragment()

            val args = Bundle()
            args.putParcelable(ARG_TV_SHOW, card)
            fragment.arguments = args

            return fragment
        }
    }
}