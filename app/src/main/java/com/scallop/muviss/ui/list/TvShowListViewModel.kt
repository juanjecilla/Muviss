package com.scallop.muviss.ui.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scallop.muviss.domain.entities.ResultWrapperEntity
import com.scallop.muviss.domain.entities.TvShowItemEntity
import com.scallop.muviss.domain.usecases.GetTopRatedTvShowsBaseUseCase
import com.scallop.muviss.domain.usecases.GetTopRatedTvShowsUseCase
import com.scallop.muviss.entities.TvShowItem
import com.scallop.muviss.mappers.TvShowMapper
import com.scallop.muviss.ui.list.TvShowListFragment.Companion.STARTING_PAGE_INDEX
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class TvShowListViewModel @Inject constructor(
    private val useCase: @JvmSuppressWildcards GetTopRatedTvShowsBaseUseCase,
    private val mapper: TvShowMapper,
    private val dispatcher: CoroutineDispatcher
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
            val results = withContext(dispatcher) {
                useCase(GetTopRatedTvShowsUseCase.Params(page))
            }
            results.map {
                _data.value = TvShowListState.TvShowListLoading(false)

                when (it) {
                    is ResultWrapperEntity.Success<*> -> {
                        items.addAll(mapper.mapResults(it as ResultWrapperEntity.Success<List<TvShowItemEntity>>).value)
                        _data.value = TvShowListState.TvShowListItems(items)
                    }

                    is ResultWrapperEntity.GenericError -> {
                        _data.value = TvShowListState.TvShowListFailure(
                            it.exception.toString()
                        )
                    }
                }


                Log.d("HELLO","HELLO1")
                val a = async {
                    delay(100)
                    1
                }
                val b = async {
                    delay(100)
                    1
                }
                val c = async {
                    delay(100)
                    1
                }
                Log.d("HELLO","HELLO2")

                Log.d("HELLO","${a.await()} ${b.await()} ${c.await()}")


            }.collect()
        }
    }
}
