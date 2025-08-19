package br.com.movieapp.feature.moviedetails.presentation.state

import androidx.compose.ui.graphics.Color
import androidx.paging.PagingData
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.core.domain.model.MovieDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class MovieDetailsState(
    val movieDetails: MovieDetails? = null,
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val iconColor: Color = Color.White,
    val results: Flow<PagingData<Movie>> = emptyFlow()
)
