package br.com.movieapp.feature.moviedetails.domain.source

import br.com.movieapp.core.data.remote.response.MovieResponse
import br.com.movieapp.core.domain.model.MovieDetails
import br.com.movieapp.core.paging.MoviesSimilarPagingSource

interface MovieDetailsRemoteDataSource {

    fun getSimilarMoviesPagingSource(movieId: Int): MoviesSimilarPagingSource
    suspend fun getMovieDetails(movieId: Int): MovieDetails
    suspend fun getMoviesSimilar(page: Int, movieId: Int): MovieResponse
}