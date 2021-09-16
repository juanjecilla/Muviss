package com.scallop.muviss.di

import com.scallop.muviss.data.repository.RepositoryImpl
import com.scallop.muviss.domain.repositories.TheMovieDbRepository
import com.scallop.muviss.domain.usecases.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
@JvmSuppressWildcards
abstract class UseCasesModule {

    companion object {
        @Provides
        fun provideGetSimilarTvShowsUseCase(
            repositoryImpl: RepositoryImpl
        ): GetSimilarTvShowsUseCase {
            return GetSimilarTvShowsUseCase(repositoryImpl as TheMovieDbRepository)
        }

        @Provides
        fun provideGetTopRatedTvShowsUseCase(
            repositoryImpl: RepositoryImpl
        ): GetTopRatedTvShowsUseCase {
            return GetTopRatedTvShowsUseCase(repositoryImpl as TheMovieDbRepository)
        }

        @Provides
        fun provideGetTvShowDetailsUseCase(
            repositoryImpl: RepositoryImpl
        ): GetTvShowDetailsUseCase {
            return GetTvShowDetailsUseCase(repositoryImpl as TheMovieDbRepository)
        }
    }

    @Binds
    abstract fun bindGetSimilarTvShowsUseCase(usecase: GetSimilarTvShowsUseCase): GetSimilarTvShowsBaseUseCase

    @Binds
    abstract fun bindGetTopRatedTvShowsUseCase(usecase: GetTopRatedTvShowsUseCase): GetTopRatedTvShowsBaseUseCase

    @Binds
    abstract fun bindGetTvShowDetailsUseCase(usecase: GetTvShowDetailsUseCase): GetTvShowDetailsBaseUseCase
}