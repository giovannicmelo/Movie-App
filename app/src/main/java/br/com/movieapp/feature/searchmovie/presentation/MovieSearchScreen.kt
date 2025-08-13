package br.com.movieapp.feature.searchmovie.presentation

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.collectAsLazyPagingItems
import br.com.movieapp.R
import br.com.movieapp.feature.searchmovie.presentation.components.MovieSearchEvent
import br.com.movieapp.feature.searchmovie.presentation.components.SearchContent
import br.com.movieapp.feature.searchmovie.presentation.state.MovieSearchState
import br.com.movieapp.ui.theme.black
import br.com.movieapp.ui.theme.white

@Composable
fun MovieSearchScreen(
    uiState: MovieSearchState,
    onEvent: (MovieSearchEvent) -> Unit,
    onFetchMovies: (String) -> Unit,
    navigateToDetail: (Int) -> Unit,
) {
    val pagingMovies = uiState.movies.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.search_movies),
                        color = white,
                    )
                },
                backgroundColor = black,
            )
        },
        content = { paddingValues ->
            SearchContent(
                paddingValues = paddingValues,
                pagingMovies = pagingMovies,
                query = uiState.query,
                onSearch = onFetchMovies,
                onQueryChangeEvent = onEvent,
                onDetailClick = navigateToDetail,
            )
        }
    )
}