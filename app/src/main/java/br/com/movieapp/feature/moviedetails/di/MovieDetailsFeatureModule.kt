package br.com.movieapp.feature.moviedetails.di

import br.com.movieapp.core.data.remote.MovieService
import br.com.movieapp.feature.moviedetails.data.repository.MovieDetailsRepositoryImpl
import br.com.movieapp.feature.moviedetails.data.source.MovieDetailsRemoteDataSourceImpl
import br.com.movieapp.feature.moviedetails.domain.repository.MovieDetailsRepository
import br.com.movieapp.feature.moviedetails.domain.source.MovieDetailsRemoteDataSource
import br.com.movieapp.feature.moviedetails.domain.usecase.GetMovieDetailsUseCase
import br.com.movieapp.feature.moviedetails.domain.usecase.GetMovieDetailsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieDetailsFeatureModule {

    @Provides
    @Singleton
    fun provideMovieDetailsDataSource(service: MovieService): MovieDetailsRemoteDataSource {
        return MovieDetailsRemoteDataSourceImpl(service)
    }

    @Provides
    @Singleton
    fun provideMovieDetailsRepository(remoteDataSource: MovieDetailsRemoteDataSource): MovieDetailsRepository {
        return MovieDetailsRepositoryImpl(remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideGetMovieDetailsUseCase(repository: MovieDetailsRepository): GetMovieDetailsUseCase {
        return GetMovieDetailsUseCaseImpl(repository)
    }
}