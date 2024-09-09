package com.iua.app.ui.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.iua.app.R
import com.iua.app.ui.navigation.AppScreens
import com.iua.app.ui.theme.AppTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    //operacion asincrona
    LaunchedEffect(key1 = true) {
        delay(3000)
        navController.popBackStack() // con esto sacamos la pantalla de splash de la pila para no poder volver
        navController.navigate(AppScreens.LoginScreen.routes)
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
                id = R.drawable._8596979_9391723
            ),
            contentDescription = "Company",
            modifier = Modifier.size(150.dp)
        )
        Text(
            text = "Bienvendos",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashPreview() {
    AppTheme {
        Splash()
    }
}