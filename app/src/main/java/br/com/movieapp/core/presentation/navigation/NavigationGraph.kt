package br.com.movieapp.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType.Companion.IntType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import br.com.movieapp.core.util.MOVIE_DETAIL_ARGUMENT_KEY
import br.com.movieapp.feature.moviedetails.presentation.MovieDetailsScreen
import br.com.movieapp.feature.moviedetails.presentation.event.MovieDetailsEvent
import br.com.movieapp.feature.moviedetails.presentation.state.MovieDetailsState
import br.com.movieapp.feature.moviedetails.presentation.viewmodel.MovieDetailsViewModel
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
                    navController.navigate(BottomNavItem.MovieDetails.passMovieId(movieId))
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
                    navController.navigate(BottomNavItem.MovieDetails.passMovieId(movieId))
                }
            )
        }
        composable(
            route = BottomNavItem.MovieDetails.route,
            arguments = listOf(
                navArgument(MOVIE_DETAIL_ARGUMENT_KEY) {
                    type = IntType
                    defaultValue = 0
                }
            )
        ) {
            val movieId = it.arguments?.getInt(MOVIE_DETAIL_ARGUMENT_KEY)
            val viewModel: MovieDetailsViewModel = hiltViewModel()
            val uiState: MovieDetailsState = viewModel.uiState
            val getMovieDetails: (MovieDetailsEvent.GetMovieDetails) -> Unit = viewModel::getMovieDetails
            MovieDetailsScreen(
                movieId = movieId,
                uiState = uiState,
                getMovieDetails = getMovieDetails
            )
        }
        composable(BottomNavItem.MovieFavorites.route) {
            //MovieFavoritesScreen()
        }
    }
}