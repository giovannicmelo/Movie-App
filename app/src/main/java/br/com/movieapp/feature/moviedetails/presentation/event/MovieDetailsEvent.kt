package br.com.movieapp.feature.moviedetails.presentation.event

sealed class MovieDetailsEvent {
    data class GetMovieDetails(val movieId: Int): MovieDetailsEvent()
}