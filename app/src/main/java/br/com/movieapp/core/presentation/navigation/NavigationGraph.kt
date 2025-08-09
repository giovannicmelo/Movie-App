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
            //MovieSearchScreen()
        }
        composable(BottomNavItem.MovieFavorites.route) {
            //MovieFavoritesScreen()
        }
    }
}