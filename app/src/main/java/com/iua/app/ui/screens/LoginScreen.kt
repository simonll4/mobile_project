package com.iua.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.iua.app.R
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.iua.app.ui.components.app.ButtonComponent
import com.iua.app.ui.components.login.DontHaveAccount
import com.iua.app.ui.components.login.ForgotPassword
import com.iua.app.ui.components.login.HeaderImage
import com.iua.app.ui.components.login.LoginForm
import com.iua.app.ui.view_models.LoginViewModel
import com.iua.app.ui.navigation.AppScreens
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(viewModel: LoginViewModel = hiltViewModel(), navController: NavController) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp), contentAlignment = Alignment.Center
    ) {
        Login(Modifier.align(Alignment.Center), viewModel, navController)
    }
}

@Composable
fun Login(modifier: Modifier, viewModel: LoginViewModel, navController: NavController) {
    val loginEnable: Boolean by viewModel.loginEnable.observeAsState(initial = false)
    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)
    val isLoginSuccessful: Boolean by viewModel.isLoginSuccessful.observeAsState(initial = false)
    val coroutineScope = rememberCoroutineScope()

    if (isLoading) {
        Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    } else if (isLoginSuccessful) {
        LaunchedEffect(Unit) {
            navController.navigate(AppScreens.HomeScreen.routes) {
                popUpTo(0) { inclusive = true } // Esto vacía toda la pila.
            }
        }
    } else {
        Column(modifier = modifier) {
            Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Column {
                    Text(
                        text = stringResource(R.string.cultural_events),
                        style = MaterialTheme.typography.displaySmall,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    HeaderImage(modifier = Modifier.align(Alignment.CenterHorizontally))
                }
            }
            LoginForm(viewModel)
            ForgotPassword(modifier = Modifier.align(Alignment.End).padding(10.dp))
            ButtonComponent(
                text = stringResource(
                    R.string.login_button
                ), enabled = loginEnable
            ) {
                coroutineScope.launch { viewModel.onButtonSelected() }
            }
            DontHaveAccount(navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    val navController = rememberNavController()
    val viewModel = LoginViewModel() // Asegúrate de proporcionar un ViewModel adecuado para la vista previa
    LoginScreen(viewModel = viewModel, navController = navController)
}