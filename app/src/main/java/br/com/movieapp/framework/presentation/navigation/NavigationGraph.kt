package br.com.movieapp.framework.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

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
            //MoviePopularScreen()
        }
        composable(BottomNavItem.MovieSearch.route) {
            //MovieSearchScreen()
        }
        composable(BottomNavItem.MovieFavorites.route) {
            //MovieFavoritesScreen()
        }
    }
}