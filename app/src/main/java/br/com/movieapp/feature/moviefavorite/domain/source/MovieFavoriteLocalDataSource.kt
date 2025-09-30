package br.com.movieapp.feature.moviefavorite.domain.source

import br.com.movieapp.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieFavoriteLocalDataSource {

    suspend fun getMovies(): Flow<List<Movie>>

    suspend fun insertMovie(movie: Movie)

    suspend fun deleteMovie(movie: Movie)

    suspend fun isFavorite(movieId: Int): Boolean
}