package br.com.movieapp.feature.searchmovie.domain.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.movieapp.core.domain.model.MovieSearch
import kotlinx.coroutines.flow.Flow

interface MovieSearchRepository {

    fun getSearchMovies(query: String, pagingConfig: PagingConfig): Flow<PagingData<MovieSearch>>
}