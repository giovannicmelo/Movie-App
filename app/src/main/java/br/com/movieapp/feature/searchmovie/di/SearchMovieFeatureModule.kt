package br.com.movieapp.feature.searchmovie.di

import br.com.movieapp.core.data.remote.MovieService
import br.com.movieapp.feature.searchmovie.data.repository.MovieSearchRepositoryImpl
import br.com.movieapp.feature.searchmovie.data.source.MovieSearchRemoteDataSourceImpl
import br.com.movieapp.feature.searchmovie.domain.repository.MovieSearchRepository
import br.com.movieapp.feature.searchmovie.domain.source.MovieSearchRemoteDataSource
import br.com.movieapp.feature.searchmovie.domain.usecase.GetMovieSearchUseCase
import br.com.movieapp.feature.searchmovie.domain.usecase.GetMovieSearchUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SearchMovieFeatureModule {

    @Provides
    @Singleton
    fun provideMovieSearchDataSource(service: MovieService): MovieSearchRemoteDataSource {
        return MovieSearchRemoteDataSourceImpl(service)
    }

    @Provides
    @Singleton
    fun provideMovieSearchRepository(remoteDataSource: MovieSearchRemoteDataSource): MovieSearchRepository {
        return MovieSearchRepositoryImpl(remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideGetMovieSearchUseCase(repository: MovieSearchRepository): GetMovieSearchUseCase {
        return GetMovieSearchUseCaseImpl(repository)
    }
}