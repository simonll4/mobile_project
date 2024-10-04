package com.iua.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.iua.app.ui.screens.*

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppScreens.SplashScreen.routes
        //startDestination = AppScreens.HomeScreen.routes
    ) {
        // Pantallas generales
        composable(AppScreens.SplashScreen.routes) {
            SplashScreen(navController = navController)
        }
        composable(AppScreens.LoginScreen.routes) {
            LoginScreen(navController = navController)
        }
        composable(AppScreens.RegisterScreen.routes) {
            RegisterScreen(navController = navController)
        }
        composable(AppScreens.TermsAndConditionsScreen.routes) {
            TermsAndConditionsScreen()
        }
        composable(AppScreens.HomeScreen.routes) {
            HomeScreen()
        }
    }
}