package com.iua.app.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class AppScreens(val routes: String){
    object SplashScreen: AppScreens("splash_screen")
    object LoginScreen: AppScreens("login_screen")
    object RegisterScreen: AppScreens("register_screen")
    object TermsAndConditionsScreen: AppScreens("terms_and_conditions_screen")
    object HomeScreen: AppScreens("home_screen")
    object ProfileScreen: AppScreens("profile_screen")
    object PersonalDataScreen: AppScreens("personal_data_screen")
    object AppCustomizationScreen: AppScreens("app_customization_screen")
    object NotificationsScreen: AppScreens("notifications_screen")
}

sealed class HomeSections(val route: String, val label: String, val icon: ImageVector) {
    object Home : HomeSections("home_section", "Home", Icons.Default.Home)
    object Favorites : HomeSections("favorites_screen", "Favoritos", Icons.Default.Favorite)
}

