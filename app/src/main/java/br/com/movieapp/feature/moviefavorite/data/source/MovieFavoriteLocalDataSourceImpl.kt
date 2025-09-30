package br.com.movieapp.feature.moviefavorite.data.source

import br.com.movieapp.core.data.local.dao.MovieDao
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.core.util.isNull
import br.com.movieapp.feature.moviefavorite.data.mapper.toMovie
import br.com.movieapp.feature.moviefavorite.data.mapper.toMovieEntity
import br.com.movieapp.feature.moviefavorite.domain.source.MovieFavoriteLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieFavoriteLocalDataSourceImpl @Inject constructor(
    private val movieDao: MovieDao,
) : MovieFavoriteLocalDataSource {

    override suspend fun getMovies(): Flow<List<Movie>> {
        return movieDao.getMovies().map { movies ->
            movies.map { it.toMovie() }
        }
    }

    override suspend fun insertMovie(movie: Movie) {
        movieDao.insertMovies(listOf(movie.toMovieEntity()))
    }

    override suspend fun deleteMovie(movie: Movie) {
        movieDao.deleteMovie(movie.toMovieEntity())
    }

    override suspend fun isFavorite(movieId: Int): Boolean {
        return movieDao.getFavoriteMovie(movieId).isNull()
    }
}