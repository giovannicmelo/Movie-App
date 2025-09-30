package br.com.movieapp.feature.moviedetails.presentation

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import br.com.movieapp.R
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.feature.moviedetails.presentation.components.MovieDetailsContent
import br.com.movieapp.feature.moviedetails.presentation.event.MovieDetailsEvent
import br.com.movieapp.feature.moviedetails.presentation.state.MovieDetailsState
import br.com.movieapp.ui.theme.black
import br.com.movieapp.ui.theme.white

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieDetailsScreen(
    movieId: Int?,
    uiState: MovieDetailsState,
    getMovieDetails: (MovieDetailsEvent.GetMovieDetails) -> Unit,
    modifier: Modifier = Modifier,
) {
    val pagingMoviesSimilar: LazyPagingItems<Movie> = uiState.results.collectAsLazyPagingItems()

    LaunchedEffect(key1 = true) {
        movieId?.let { getMovieDetails(MovieDetailsEvent.GetMovieDetails(it)) }
    }

    Scaffold(
        topBar = { BuildTopAppBar(stringResource(R.string.detail_movie)) },
        content = {
            MovieDetailsContent(
                movieDetails = uiState.movieDetails,
                pagingMoviesSimilar = pagingMoviesSimilar,
                isLoading = uiState.isLoading,
                errorMessage = uiState.errorMessage,
                iconColor = uiState.iconColor,
                onAddFavoriteClick = {},
            )
        },
        modifier = modifier,
    )
}

@Composable
private fun BuildTopAppBar(title: String = "") {
    TopAppBar(
        title = { Text(text = title, color = white) },
        backgroundColor = black,
    )
}