package com.iua.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.iua.app.ui.screens.home.FavoriteScreen
import com.iua.app.ui.screens.home.HomeScreenContent

@Composable
fun BottomBarNavigation(homeNavController: NavHostController, mainNavController: NavHostController) {
    NavHost(
        navController = homeNavController,
        startDestination = HomeSections.Home.route
    ) {
        composable(HomeSections.Home.route) {
            HomeScreenContent(navController = mainNavController)
        }
        composable(HomeSections.Favorites.route) {
            FavoriteScreen()
        }
    }
}