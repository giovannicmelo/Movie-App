package br.com.movieapp.feature.searchmovie.data.mapper

import br.com.movieapp.core.data.remote.model.MovieResult
import br.com.movieapp.core.data.remote.model.SearchResult
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.core.domain.model.MovieSearch
import br.com.movieapp.core.util.orEmpty
import br.com.movieapp.core.util.toPostUrl

fun List<SearchResult>.toMovieSearch() = map {
    MovieSearch(
        id = it.id,
        voteAverage = it.voteAverage,
        imageUrl = it.posterPath.toPostUrl().orEmpty(),
    )
}