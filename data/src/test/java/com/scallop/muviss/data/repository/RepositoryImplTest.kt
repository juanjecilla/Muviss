package com.scallop.muviss.data.repository

import com.google.common.truth.Truth
import com.scallop.muviss.data.BaseTest
import com.scallop.muviss.data.mappers.DataEntityMapper
import com.scallop.muviss.domain.entities.ResultWrapperEntity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class RepositoryImplTest : BaseTest() {

    private lateinit var repository: RepositoryImpl

    @Before
    override fun setup() {
        super.setup()
        repository = RepositoryImpl(RemoteDataSourceImpl(api), DataEntityMapper())
    }

    @Test
    fun `get top rated list with succesful results`() {
        runBlocking {
            val results = repository.getTopRatedTvShows(1)
            results.collect {
                Truth.assertThat(it).isNotNull()
                Truth.assertThat(it).isInstanceOf(ResultWrapperEntity.Success::class.java)
                assert(it is ResultWrapperEntity.Success)
                it as ResultWrapperEntity.Success
                Truth.assertThat(it.value).hasSize(20)
            }
        }
    }
}
