package com.iua.app.ui.navigation

sealed class AppScreens(val routes: String){
    object SplashScreen: AppScreens("splash_screen")
    object LoginScreen: AppScreens("login_screen")
    object RegisterScreen: AppScreens("register_screen")
    object TermsAndConditionsScreen: AppScreens("terms_and_conditions_screen")
    object HomeScreen: AppScreens("home_screen")
}