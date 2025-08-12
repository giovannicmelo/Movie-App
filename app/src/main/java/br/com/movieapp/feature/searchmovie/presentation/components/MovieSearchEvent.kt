package br.com.movieapp.feature.searchmovie.presentation.components

sealed class MovieSearchEvent {
    data class EnteredQuery(val value: String) : MovieSearchEvent()
}