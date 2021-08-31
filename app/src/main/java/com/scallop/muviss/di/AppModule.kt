package com.scallop.muviss.di

import com.scallop.muviss.data.api.TheMovieDbApi
import com.scallop.muviss.data.mappers.DataEntityMapper
import com.scallop.muviss.data.repository.RemoteDataSource
import com.scallop.muviss.data.repository.RemoteDataSourceImpl
import com.scallop.muviss.data.repository.RepositoryImpl
import com.scallop.muviss.domain.repositories.TheMovieDbRepository
import com.scallop.muviss.domain.usecases.GetTopRatedTvShowsUseCase
import com.scallop.muviss.mappers.TvShowMapper
import com.scallop.muviss.ui.list.TvShowListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

@Suppress("USELESS_CAST") // It is important to maintain the dependency tree
val repositoryModule = module {
    single {
        RemoteDataSourceImpl(
            get()
        ) as RemoteDataSource
    }
    single {
        RepositoryImpl(
            get(),
            DataEntityMapper()
        ) as TheMovieDbRepository
    }
}

val useCaseModule = module {
    factory { GetTopRatedTvShowsUseCase(get()) }
}

val networkModule = module {
    single { createNetworkClient(BASE_URL, get()) }
    single { (get() as Retrofit).create(TheMovieDbApi::class.java) }
}

val viewModelModule = module {
    viewModel {
        TvShowListViewModel(get(GetTopRatedTvShowsUseCase::class), TvShowMapper())
    }
}

private const val BASE_URL = "https://api.themoviedb.org/"
