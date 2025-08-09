package br.com.movieapp.feature.moviepopular.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import br.com.movieapp.feature.moviepopular.domain.usecase.GetPopularMoviesUseCase
import br.com.movieapp.feature.moviepopular.presentation.state.MoviePopularState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviePopularViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
): ViewModel() {

    var uiState: MoviePopularState by mutableStateOf(MoviePopularState())
        private set

    init {
        getPopularMovies()
    }

    private fun getPopularMovies() {
        val movies = getPopularMoviesUseCase().cachedIn(viewModelScope)
        uiState = uiState.copy(movies = movies)
    }
}