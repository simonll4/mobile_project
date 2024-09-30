package com.iua.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.iua.app.mock.eventsMock
import com.iua.app.ui.screens.FavoriteScreen
import com.iua.app.ui.screens.HomeScreenContent
import com.iua.app.ui.screens.ProfileScreen

@Composable
fun BottomBarNavigation(homeNavController: NavHostController) {
    NavHost(
        navController = homeNavController,
        startDestination = HomeSections.Home.route
    ) {
        composable(HomeSections.Home.route) {
            HomeScreenContent()
        }
        composable(HomeSections.Favorites.route) {
            FavoriteScreen()
        }
        composable(HomeSections.Profile.route) {
            ProfileScreen()
        }
    }
}