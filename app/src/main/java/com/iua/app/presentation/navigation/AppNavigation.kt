package com.iua.app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.iua.app.presentation.auth.login.LoginScreen
import com.iua.app.presentation.auth.login.LoginViewModel
import com.iua.app.presentation.auth.login.SplashScreen
import com.iua.app.presentation.home.HomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreens.SplashScreen.routes,
    ) {
        composable(AppScreens.SplashScreen.routes) {
            SplashScreen(navController)
        }
        composable(AppScreens.LoginScreen.routes) {
            LoginScreen(LoginViewModel(), navController)
        }
        composable(AppScreens.HomeScreen.routes) {
            HomeScreen()
        }
    }
}