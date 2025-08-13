package br.com.movieapp.feature.searchmovie.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import br.com.movieapp.feature.searchmovie.domain.usecase.GetMovieSearchUseCase
import br.com.movieapp.feature.searchmovie.presentation.components.MovieSearchEvent
import br.com.movieapp.feature.searchmovie.presentation.state.MovieSearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieSearchViewModel @Inject constructor(
    private val getMovieSearchUseCase: GetMovieSearchUseCase,
) : ViewModel() {

    var uiState: MovieSearchState by mutableStateOf(MovieSearchState())
        private set

    fun fetchMovies(query: String = "") {
        val movies = getMovieSearchUseCase(
            params = GetMovieSearchUseCase.Params(query = query)
        ).cachedIn(viewModelScope)
        uiState = uiState.copy(movies = movies)
    }

    fun event(event: MovieSearchEvent) {
        uiState = when(event) {
            is MovieSearchEvent.EnteredQuery -> {
                uiState.copy(query = event.value)
            }
        }
    }
}