package br.com.movieapp.feature.moviedetails.data.source

import br.com.movieapp.core.data.remote.MovieService
import br.com.movieapp.core.data.remote.response.MovieResponse
import br.com.movieapp.core.domain.model.MovieDetails
import br.com.movieapp.core.paging.MoviesSimilarPagingSource
import br.com.movieapp.core.util.toBackdropUrl
import br.com.movieapp.feature.moviedetails.domain.source.MovieDetailsRemoteDataSource
import javax.inject.Inject

class MovieDetailsRemoteDataSourceImpl @Inject constructor(
    private val service: MovieService,
) : MovieDetailsRemoteDataSource {

    override fun getSimilarMoviesPagingSource(movieId: Int): MoviesSimilarPagingSource {
        return MoviesSimilarPagingSource(this, movieId)
    }

    override suspend fun getMovieDetails(movieId: Int): MovieDetails {
        val response = service.getMovie(movieId)
        return MovieDetails(
            id = response.id,
            title = response.title,
            overview = response.overview,
            genres = response.genres.map { it.name },
            releaseDate = response.releaseDate,
            voteAverage = response.voteAverage,
            voteCount = response.voteCount,
            backdropPathUrl = response.backdropPath.toBackdropUrl(),
        )
    }

    override suspend fun getMoviesSimilar(
        page: Int,
        movieId: Int
    ): MovieResponse {
        return service.getSimilarMovies(movieId, page)
    }
}