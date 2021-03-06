package com.scallop.muviss.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.scallop.muviss.R
import com.scallop.muviss.common.Properties
import com.scallop.muviss.databinding.FragmentTvShowDetailBinding
import com.scallop.muviss.ui.commons.viewBinding
import com.scallop.muviss.ui.commons.visible
import com.scallop.muviss.ui.commons.visibleWithExpandAnimation
import com.scallop.muviss.ui.list.TvShowListFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvShowDetailFragment : Fragment() {

    private val viewModel: TvShowDetailViewModel by viewModels()

    private val binding by viewBinding<FragmentTvShowDetailBinding>()
    private val args: TvShowDetailFragmentArgs by navArgs()
    private var tvShowId = -1L

    private lateinit var adapter: TvShowDetailPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tvShowId = args.tvShowId
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)

        return inflater.inflate(R.layout.fragment_tv_show_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            adapter = TvShowDetailPagerAdapter(fm = childFragmentManager, lifecycle = lifecycle)

            detailViewPager.adapter = adapter
            detailViewPager.registerOnPageChangeCallback(object :
                    ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                        if (position > adapter.itemCount - THRESHOLD) {
                            viewModel.getSimilarTvShows(
                                tvShowId,
                                (adapter.itemCount / Properties.ITEMS_PER_PAGE) + 1
                            )
                        }

                        seeDetailsButton.visibleWithExpandAnimation(position != 0)
                    }
                })

            seeDetailsButton.setOnClickListener {
                val currentTvShowId = adapter.getItemIdAtPosition(detailViewPager.currentItem)
                currentTvShowId?.let {
                    val action = TvShowListFragmentDirections.showDetail()
                    action.tvShowId = it

                    val navController = view.findNavController()
                    navController.navigate(action)
                }
            }
        }

        viewModel.data.observe(viewLifecycleOwner, {
            with(binding) {
                when (it) {
                    is TvShowDetailState.TvShowDetailLoading -> {
                        progressBar.visible(it.show)
                    }
                    is TvShowDetailState.TvShowDetailItems -> {
                        this.progressBar.visibility = View.GONE
                        adapter.addItems(listOf(it.item))
                    }
                    is TvShowDetailState.TvShowDetailRelatedItems -> {
                        this.progressBar.visibility = View.GONE
                        adapter.addItems(it.items)
                    }
                    is TvShowDetailState.TvShowDetailFailure -> {
                        Toast.makeText(context, it.failure, Toast.LENGTH_LONG).show()
                    }
                }
            }
        })

        viewModel.getTvShowDetails(tvShowId)
    }

    companion object {
        private const val THRESHOLD = 2
    }
}
