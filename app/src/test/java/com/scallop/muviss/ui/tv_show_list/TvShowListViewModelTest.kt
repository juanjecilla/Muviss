package com.scallop.muviss.ui.tv_show_list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.scallop.muviss.CoroutineTestRule
import com.scallop.muviss.TestUtils
import com.scallop.muviss.fakes.FakeGetTopRatedTvShowsUseCase
import com.scallop.muviss.mappers.TvShowMapper
import com.scallop.muviss.ui.list.TvShowListState
import com.scallop.muviss.ui.list.TvShowListViewModel
import com.scallop.muviss.utils.Status
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

    private lateinit var viewModel: TvShowListViewModel
    private lateinit var mapper: TvShowMapper

    @Mock
    lateinit var observer: Observer<TvShowListState>

    lateinit var openMocks: AutoCloseable

    @Before
    fun setup() {
        openMocks = MockitoAnnotations.openMocks(this)
        mapper = TvShowMapper()
        viewModel = TvShowListViewModel(
            FakeGetTopRatedTvShowsUseCase(Status.SUCCESSFUL),
            mapper,
            coroutineTestRule.dispatcher
        )
        viewModel.data.observeForever(observer)
    }

    @Test
    fun `getting items on viewModel init with successful result`() {
        Mockito.verify(observer).onChanged(
            TvShowListState.TvShowListItems(
                mapper.mapTvShowItems(
                    TestUtils.getTvShows(20)
                )
            )
        )
    }
}
