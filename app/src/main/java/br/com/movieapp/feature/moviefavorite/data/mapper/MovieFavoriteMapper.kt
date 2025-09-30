package br.com.movieapp.feature.moviefavorite.data.mapper

import br.com.movieapp.core.data.local.entity.MovieEntity
import br.com.movieapp.core.domain.model.Movie

fun MovieEntity.toMovie() = Movie(
    id = movieId,
    title = title,
    imageUrl = imageUrl,
)

fun Movie.toMovieEntity() = MovieEntity(
    movieId = id,
    title = title,
    imageUrl = imageUrl,
)