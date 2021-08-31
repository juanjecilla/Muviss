package com.scallop.muviss.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scallop.muviss.domain.entities.ResultWrapperEntity
import com.scallop.muviss.domain.entities.TvShowItemEntity
import com.scallop.muviss.domain.usecases.GetTopRatedTvShowsUseCase
import com.scallop.muviss.entities.TvShowItem
import com.scallop.muviss.mappers.TvShowMapper
import com.scallop.muviss.ui.list.TvShowListFragment.Companion.STARTING_PAGE_INDEX
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TvShowListViewModel(
    private val useCase: GetTopRatedTvShowsUseCase,
    private val mMapper: TvShowMapper,
    private val mDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _data = MutableLiveData<TvShowListState>()
    val data: LiveData<TvShowListState> get() = _data

    private val items = mutableListOf<TvShowItem>()

    init {
        getTopRatedTShows(STARTING_PAGE_INDEX)
    }

    fun getTopRatedTShows(page: Int) {
        _data.value = TvShowListState.TvShowListLoading(true)
        viewModelScope.launch {
            val results = withContext(mDispatcher) {
                useCase(GetTopRatedTvShowsUseCase.Params(page))
            }
            results.map {
                _data.value = TvShowListState.TvShowListLoading(false)

                when (it) {
                    is ResultWrapperEntity.Success<*> -> {
                        items.addAll(mMapper.mapResults(it as ResultWrapperEntity.Success<List<TvShowItemEntity>>).value)
                        _data.value = TvShowListState.TvShowListItems(items)
                    }

                    is ResultWrapperEntity.GenericError -> {
                        _data.value = TvShowListState.TvShowListFailure(
                            it.exception.toString()
                        )
                    }
                    else -> throw IllegalArgumentException()
                }
            }.collect()
        }
    }
}