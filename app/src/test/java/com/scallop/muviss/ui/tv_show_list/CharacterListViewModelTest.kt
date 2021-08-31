package com.scallop.muviss.ui.tv_show_list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.scallop.muviss.CoroutineTestRule
import com.scallop.muviss.utils.Status
import com.scallop.muviss.TestUtils
import com.scallop.muviss.fakes.FakeGetTopRatedTvShowsUseCase
import com.scallop.muviss.mappers.TvShowMapper
import com.scallop.muviss.ui.list.TvShowListState
import com.scallop.muviss.ui.list.TvShowListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class TvShowListViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var mViewModel: TvShowListViewModel
    private lateinit var mMapper: TvShowMapper

    @Mock
    lateinit var mObserver: Observer<TvShowListState>

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mMapper = TvShowMapper()
        mViewModel = TvShowListViewModel(
            FakeGetTopRatedTvShowsUseCase(Status.SUCCESSFUL),
            mMapper,
            coroutineTestRule.dispatcher
        )
        mViewModel.data.observeForever(mObserver)
    }

    @Test
    fun `getting items on viewModel init with successful result`() {
        Mockito.verify(mObserver).onChanged(
            TvShowListState.TvShowListItems(
                mMapper.mapTvShowItems(
                    TestUtils.getTvShows(20)
                )
            )
        )
    }
}