package br.com.movieapp.feature.moviedetails.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.movieapp.core.util.ResultData
import br.com.movieapp.core.util.UtilFunctions
import br.com.movieapp.feature.moviedetails.domain.usecase.GetMovieDetailsUseCase
import br.com.movieapp.feature.moviedetails.presentation.event.MovieDetailsEvent
import br.com.movieapp.feature.moviedetails.presentation.state.MovieDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
): ViewModel() {

    var uiState: MovieDetailsState by mutableStateOf(MovieDetailsState())
        private set

    fun getMovieDetails(getMovieDetails: MovieDetailsEvent) {
        event(getMovieDetails)
    }

    private fun event(event: MovieDetailsEvent) {
        when (event) {
            is MovieDetailsEvent.GetMovieDetails -> {
                viewModelScope.launch {
                    getMovieDetailsUseCase(
                        params = GetMovieDetailsUseCase.Params(
                            movieId = event.movieId
                        )
                    ).collect { result ->
                        when (result) {
                            is ResultData.Success -> {
                                uiState = uiState.copy(
                                    isLoading = false,
                                    movieDetails = result.data?.second,
                                    results = result.data?.first ?: emptyFlow(),
                                )
                            }
                            is ResultData.Failure -> {
                                uiState = uiState.copy(
                                    isLoading = false,
                                    errorMessage = result.exception?.message.orEmpty()
                                )
                                UtilFunctions.logError("DETAILS-ERROR", result.exception?.message.orEmpty())
                            }
                            is ResultData.Loading -> {
                                uiState = uiState.copy(
                                    isLoading = true,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}