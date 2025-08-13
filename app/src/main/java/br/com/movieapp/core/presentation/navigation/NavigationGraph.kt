package br.com.movieapp.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.movieapp.feature.moviepopular.presentation.MoviePopularScreen
import br.com.movieapp.feature.moviepopular.presentation.state.MoviePopularState
import br.com.movieapp.feature.moviepopular.presentation.viewmodel.MoviePopularViewModel
import br.com.movieapp.feature.searchmovie.presentation.MovieSearchScreen
import br.com.movieapp.feature.searchmovie.presentation.components.MovieSearchEvent
import br.com.movieapp.feature.searchmovie.presentation.state.MovieSearchState
import br.com.movieapp.feature.searchmovie.presentation.viewmodel.MovieSearchViewModel

@Composable
fun NavigationGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.MoviePopular.route,
        modifier = modifier
    ) {
        composable(BottomNavItem.MoviePopular.route) {
            val viewModel: MoviePopularViewModel = hiltViewModel()
            val uiState: MoviePopularState = viewModel.uiState

            MoviePopularScreen(
                uiState = uiState,
                navigateToDetail = { movieId ->

                }
            )
        }
        composable(BottomNavItem.MovieSearch.route) {
            val viewModel: MovieSearchViewModel = hiltViewModel()
            val uiState: MovieSearchState = viewModel.uiState
            val onEvent: (MovieSearchEvent) -> Unit = viewModel::event
            val onFetchMovies: (String) -> Unit = viewModel::fetchMovies

            MovieSearchScreen(
                uiState = uiState,
                onEvent = onEvent,
                onFetchMovies = onFetchMovies,
                navigateToDetail = { movieId ->

                }
            )
        }
        composable(BottomNavItem.MovieFavorites.route) {
            //MovieFavoritesScreen()
        }
    }
}