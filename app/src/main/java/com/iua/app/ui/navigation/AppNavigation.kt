package com.iua.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.iua.app.ui.screens.*
import com.iua.app.ui.screens.auth.LoginScreen
import com.iua.app.ui.screens.home.EventDetailScreen
import com.iua.app.ui.screens.home.HomeScreen
import com.iua.app.ui.screens.profile.AppCustomizationScreen
import com.iua.app.ui.screens.profile.NotificationsScreen
import com.iua.app.ui.screens.profile.PersonalDataScreen
import com.iua.app.ui.screens.profile.ProfileScreen


@Composable
fun AppNavigation(startEventId: String?,isUserLoggedIn: Boolean,) {
    val navController = rememberNavController()

    val startDestination = when {
        startEventId != null && !isUserLoggedIn -> AppScreens.SplashScreen.routes
        startEventId != null -> AppScreens.HomeScreen.routes
        isUserLoggedIn -> AppScreens.HomeScreen.routes
        else -> AppScreens.SplashScreen.routes
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(AppScreens.SplashScreen.routes) {
            SplashScreen(
                navController = navController,
                isUserLoggedIn = isUserLoggedIn,
                startEventId = startEventId
            )
        }
        composable(AppScreens.LoginScreen.routes) {
            LoginScreen(navController = navController)
        }
        composable(AppScreens.HomeScreen.routes) {
            HomeScreen(navController = navController)
        }
        composable("${AppScreens.EventDetailScreen.routes}/{eventId}") { backStackEntry ->
            val eventId = backStackEntry.arguments?.getString("eventId") ?: return@composable
            EventDetailScreen(navController = navController, eventId = eventId)
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



//@Composable
//fun AppNavigation(startEventId: String?) {
//    val navController = rememberNavController()
//
//    NavHost(
//        navController = navController,
//        startDestination = AppScreens.SplashScreen.routes
//        //startDestination = AppScreens.HomeScreen.routes
//    ) {
//        composable(AppScreens.SplashScreen.routes) {
//            SplashScreen(navController = navController)
//        }
//        composable(AppScreens.LoginScreen.routes) {
//            LoginScreen(navController = navController)
//        }
//        composable(AppScreens.RegisterScreen.routes) {
//            RegisterScreen(navController = navController)
//        }
//        composable(AppScreens.TermsAndConditionsScreen.routes) {
//            TermsAndConditionsScreen(navController = navController)
//        }
//        composable(AppScreens.HomeScreen.routes) {
//            HomeScreen(navController = navController)
//        }
//        composable("${AppScreens.EventDetailScreen.routes}/{eventId}") { backStackEntry ->
//            val eventId = backStackEntry.arguments?.getString("eventId") ?: return@composable
//            EventDetailScreen(navController = navController, eventId = eventId)
//        }
//        composable(AppScreens.ProfileScreen.routes) {
//            ProfileScreen(navController = navController)
//        }
//        composable(AppScreens.PersonalDataScreen.routes) {
//            PersonalDataScreen(navController = navController)
//        }
//        composable(AppScreens.AppCustomizationScreen.routes) {
//            AppCustomizationScreen(navController = navController)
//        }
//        composable(AppScreens.NotificationsScreen.routes) {
//            NotificationsScreen(navController = navController)
//        }
//    }
//}