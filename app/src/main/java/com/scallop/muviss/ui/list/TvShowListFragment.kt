package com.scallop.muviss.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scallop.muviss.R
import com.scallop.muviss.databinding.FragmentTvShowListBinding
import com.scallop.muviss.entities.TvShowItem
import com.scallop.muviss.ui.commons.EndlessRecyclerViewScrollListener
import com.scallop.muviss.ui.commons.viewBinding
import com.scallop.muviss.ui.commons.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvShowListFragment : Fragment() {

    private val viewModel: TvShowListViewModel by viewModels()

    private val binding by viewBinding<FragmentTvShowListBinding>()

    private lateinit var adapter: TvShowAdapter
    private lateinit var layoutManager: GridLayoutManager
    private lateinit var endlessRecyclerViewScrollListener: EndlessRecyclerViewScrollListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = TvShowAdapter {
            showDetail(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)

        return inflater.inflate(R.layout.fragment_tv_show_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        layoutManager = GridLayoutManager(context, 2)

        endlessRecyclerViewScrollListener = object : EndlessRecyclerViewScrollListener(
            layoutManager,
            STARTING_PAGE_INDEX
        ) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                viewModel.getTopRatedTShows(page)
            }
        }

        with(binding) {
            tvShowList.adapter = adapter
            tvShowList.layoutManager = layoutManager
            tvShowList.addOnScrollListener(endlessRecyclerViewScrollListener)
        }

        viewModel.data.observe(viewLifecycleOwner, {
            with(binding) {
                when (it) {
                    is TvShowListState.TvShowListLoading -> {
                        progressBar.visible(it.show)
                    }
                    is TvShowListState.TvShowListItems -> {
                        this.progressBar.visibility = View.GONE
                        updateList(it.items)
                    }
                    is TvShowListState.TvShowListFailure -> {
                        Toast.makeText(context, it.failure, Toast.LENGTH_LONG).show()
                    }
                    else -> throw IllegalArgumentException()
                }
            }
        })
    }

    private fun updateList(items: List<TvShowItem>) {
        adapter.submitList(items)

        with(binding) {
            if (adapter.itemCount == 0) {
                emptyLabel.visible(true)
                tvShowList.visible(false)
            } else {
                emptyLabel.visible(false)
                tvShowList.visible(true)
            }
        }
    }

    private fun showDetail(item: TvShowItem) {
        item.id?.let {
            val action = TvShowListFragmentDirections.showDetail()
            action.tvShowId = it

            val navController = view?.findNavController()
            navController?.navigate(action)
        }
    }

    companion object {
        const val STARTING_PAGE_INDEX = 1
    }
}
