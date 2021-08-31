package com.scallop.muviss.data.repository

import com.google.common.truth.Truth
import com.scallop.muviss.data.BaseTest
import com.scallop.muviss.data.entitites.ResultWrapperData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class RemoteDataSourceImplTest : BaseTest() {

    private lateinit var remote: RemoteDataSourceImpl

    @Before
    override fun setup() {
        super.setup()
        remote = RemoteDataSourceImpl(api)
    }

    @Test
    fun `get top rated list with successful results`() {
        runBlocking {
            val results = remote.getTopRatedTvShows(1)
            results.collect {
                Truth.assertThat(it).isNotNull()
                Truth.assertThat(it).isInstanceOf(ResultWrapperData.Success::class.java)
                assert(it is ResultWrapperData.Success)
                it as ResultWrapperData.Success
                Truth.assertThat(it.value).hasSize(20)
            }
        }
    }
}