package com.iua.app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.iua.app.R
import com.iua.app.ui.navigation.AppScreens
import kotlinx.coroutines.delay


//@Composable
//fun SplashScreen(navController: NavController) {
//    LaunchedEffect(key1 = true) {
//        delay(3000)
//        navController.popBackStack() // con esto sacamos la pantalla de splash de la pila para no poder volver
//        navController.navigate(AppScreens.LoginScreen.routes)
//    }
//    Splash()
//}

@Composable
fun SplashScreen(navController: NavHostController, isUserLoggedIn: Boolean, startEventId: String?) {
    LaunchedEffect(Unit) {
        // Simula el tiempo de carga
        delay(2000L)

        // Decide la ruta a la que se navega
        when {
            startEventId != null && isUserLoggedIn -> {
                // Usuario logueado y llegó desde una notificación
                navController.navigate("${AppScreens.EventDetailScreen.routes}/$startEventId") {
                    popUpTo(AppScreens.SplashScreen.routes) { inclusive = true }
                }
            }

            isUserLoggedIn -> {
                // Usuario logueado y sin notificación
                navController.navigate(AppScreens.HomeScreen.routes) {
                    popUpTo(AppScreens.SplashScreen.routes) { inclusive = true }
                }
            }

            else -> {
                // Usuario no logueado
                navController.navigate(AppScreens.LoginScreen.routes) {
                    popUpTo(AppScreens.SplashScreen.routes) { inclusive = true }
                }
            }
        }
    }

    Splash()
}


@Composable
fun Splash() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(
                id = R.drawable.logo_prev
            ),
            contentDescription = "Company",
            modifier = Modifier.size(250.dp)
        )
        Text(
            text = stringResource(R.string.splash_title),
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.onBackground,
        )
    }
}