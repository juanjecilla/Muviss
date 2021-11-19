package com.scallop.muviss.di

import com.scallop.muviss.data.api.TheMovieDbApi
import com.scallop.muviss.data.mappers.DataEntityMapper
import com.scallop.muviss.data.repository.RemoteDataSource
import com.scallop.muviss.data.repository.RemoteDataSourceImpl
import com.scallop.muviss.data.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideRepository(
        remoteDataSource: RemoteDataSource,
        mapper: DataEntityMapper
    ): RepositoryImpl {
        return RepositoryImpl(remoteDataSource, mapper)
    }

    @Provides
    @Singleton
    fun provideRemote(api: TheMovieDbApi): RemoteDataSource {
        return RemoteDataSourceImpl(api)
    }

    @Provides
    fun provideDataEntityMapper(): DataEntityMapper {
        return DataEntityMapper()
    }
}
