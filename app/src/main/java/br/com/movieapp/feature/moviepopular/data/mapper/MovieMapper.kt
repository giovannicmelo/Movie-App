package br.com.movieapp.feature.moviepopular.data.mapper

import br.com.movieapp.core.data.remote.model.MovieResult
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.core.util.orEmpty
import br.com.movieapp.core.util.toPostUrl

fun List<MovieResult>.toMovie() = map {
    Movie(
        id = it.id,
        title = it.title,
        voteAverage = it.voteAverage,
        imageUrl = it.posterPath?.toPostUrl().orEmpty(),
    )
}