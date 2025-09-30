package br.com.movieapp.feature.moviefavorite.data.repository

import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.feature.moviefavorite.domain.repository.MovieFavoriteRepository
import br.com.movieapp.feature.moviefavorite.domain.source.MovieFavoriteLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieFavoriteRepositoryImpl @Inject constructor(
    private val localDataSource: MovieFavoriteLocalDataSource,
) : MovieFavoriteRepository {

    override suspend fun getMovies(): Flow<List<Movie>> {
        return localDataSource.getMovies()
    }

    override suspend fun insertMovie(movie: Movie) {
        return localDataSource.insertMovie(movie)
    }

    override suspend fun deleteMovie(movie: Movie) {
        return localDataSource.deleteMovie(movie)
    }

    override suspend fun isFavorite(movieId: Int): Boolean {
        return localDataSource.isFavorite(movieId)
    }
}