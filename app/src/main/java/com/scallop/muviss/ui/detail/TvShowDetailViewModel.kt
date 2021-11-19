package com.scallop.muviss.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scallop.muviss.domain.entities.ResultWrapperEntity
import com.scallop.muviss.domain.entities.TvShowDetailEntity
import com.scallop.muviss.domain.entities.TvShowItemEntity
import com.scallop.muviss.domain.usecases.GetSimilarTvShowsBaseUseCase
import com.scallop.muviss.domain.usecases.GetSimilarTvShowsUseCase
import com.scallop.muviss.domain.usecases.GetTvShowDetailsBaseUseCase
import com.scallop.muviss.domain.usecases.GetTvShowDetailsUseCase
import com.scallop.muviss.mappers.TvShowMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TvShowDetailViewModel @Inject constructor(
    private val detailsUseCase: @JvmSuppressWildcards GetTvShowDetailsBaseUseCase,
    private val similarUseCase: @JvmSuppressWildcards GetSimilarTvShowsBaseUseCase,
    private val mapper: TvShowMapper,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _data = MutableLiveData<TvShowDetailState>()
    val data: LiveData<TvShowDetailState> get() = _data

    fun getTvShowDetails(id: Long) {
        _data.value = TvShowDetailState.TvShowDetailLoading(true)
        viewModelScope.launch {
            val result = withContext(dispatcher) {
                detailsUseCase(GetTvShowDetailsUseCase.Params(id))
            }
            result.map {
                _data.value = TvShowDetailState.TvShowDetailLoading(false)

                when (it) {
                    is ResultWrapperEntity.Success<*> -> {
                        _data.value =
                            TvShowDetailState.TvShowDetailItems(
                                mapper.mapTvShowDetailResult(
                                    it as ResultWrapperEntity.Success<TvShowDetailEntity>
                                ).value
                            )
                    }

                    is ResultWrapperEntity.GenericError -> {
                        _data.value = TvShowDetailState.TvShowDetailFailure(
                            it.exception.toString()
                        )
                    }
                    else -> throw IllegalArgumentException()
                }
            }.collect()
        }
    }

    fun getSimilarTvShows(id: Long, page: Int) {
        _data.value = TvShowDetailState.TvShowDetailLoading(true)
        viewModelScope.launch {
            val result = withContext(dispatcher) {
                similarUseCase(GetSimilarTvShowsUseCase.Params(id, page))
            }
            result.map {
                _data.value = TvShowDetailState.TvShowDetailLoading(false)

                when (it) {
                    is ResultWrapperEntity.Success<*> -> {
                        _data.value =
                            TvShowDetailState.TvShowDetailRelatedItems(
                                mapper.mapResults(
                                    it as ResultWrapperEntity.Success<List<TvShowItemEntity>>
                                )
                                    .value
                            )
                    }

                    is ResultWrapperEntity.GenericError -> {
                        _data.value = TvShowDetailState.TvShowDetailFailure(
                            it.exception.toString()
                        )
                    }
                    else -> throw IllegalArgumentException()
                }
            }.collect()
        }
    }
}
