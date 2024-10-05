package com.iua.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.iua.app.ui.screens.*
import com.iua.app.ui.screens.profile.AppCustomizationScreen
import com.iua.app.ui.screens.profile.NotificationsScreen
import com.iua.app.ui.screens.profile.PersonalDataScreen
import com.iua.app.ui.screens.profile.ProfileScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        //startDestination = AppScreens.SplashScreen.routes
        //startDestination = AppScreens.HomeScreen.routes
        startDestination = AppScreens.ProfileScreen.routes
    ) {
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
            HomeScreen(navController = navController)
        }
        composable(AppScreens.ProfileScreen.routes) {
            ProfileScreen(navController = navController)
        }
        composable(AppScreens.PersonalDataScreen.routes) {
            PersonalDataScreen(navController = navController)
        }
        composable(AppScreens.AppCustomizationScreen.routes) {
            AppCustomizationScreen(navController = navController)
        }
        composable(AppScreens.NotificationsScreen.routes) {
            NotificationsScreen(navController = navController)
        }
    }
}