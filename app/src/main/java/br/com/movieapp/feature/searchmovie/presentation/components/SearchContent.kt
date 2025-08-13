package br.com.movieapp.feature.searchmovie.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import br.com.movieapp.core.domain.model.MovieSearch
import br.com.movieapp.core.presentation.components.common.ErrorScreen
import br.com.movieapp.core.presentation.components.common.LoadingView
import br.com.movieapp.feature.moviepopular.presentation.components.MovieItem
import br.com.movieapp.ui.theme.black

@Composable
fun SearchContent(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(),
    pagingMovies: LazyPagingItems<MovieSearch>,
    query: String,
    onSearch: (String) -> Unit,
    onQueryChangeEvent: (MovieSearchEvent) -> Unit,
    onDetailClick: (Int) -> Unit,
) {
    var isLoading: Boolean by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(black),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        SearchComponent(
            query = query,
            onSearch = {
                isLoading = true
                onSearch(it)
            },
            onQueryChangeEvent = onQueryChangeEvent,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
        )
        Spacer(modifier = Modifier.height(12.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = paddingValues,
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize(),
        ) {
            items(pagingMovies.itemCount) { i ->
                pagingMovies[i]?.let { movie ->
                    MovieItem(
                        voteAverage = movie.voteAverage,
                        imageUrl = movie.imageUrl,
                        id = movie.id,
                        onItemClick = onDetailClick,
                    )
                }
                isLoading = false
            }
            pagingMovies.apply {
                when {
                    isLoading -> {
                        item(span = { GridItemSpan(maxLineSpan) }) {
                            LoadingView()
                        }
                    }
                    loadState.refresh is LoadState.Error -> {
                        isLoading = false
                        item(span = { GridItemSpan(maxLineSpan) }) {
                            ErrorScreen(
                                message = "Não foi possível carregar os filmes",
                                onRetry = { retry() }
                            )
                        }
                    }
                    loadState.append is LoadState.Error -> {
                        isLoading = false
                        item(span = { GridItemSpan(maxLineSpan) }) {
                            ErrorScreen(
                                message = "Não foi possível carregar os filmes",
                                onRetry = { retry() }
                            )
                        }
                    }
                }
            }
        }
    }
}