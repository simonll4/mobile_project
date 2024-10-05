package com.iua.app.ui.screens.Auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.iua.app.R
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.iua.app.ui.components.ButtonComponent
import com.iua.app.ui.components.ClickableTextComponent
import com.iua.app.ui.components.FieldTextComponent
import com.iua.app.ui.components.FieldType
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

@Composable
fun HeaderImage(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.logo_prev),
        contentDescription = "Header Image",
        modifier = modifier.size(200.dp)
    )
}

@Composable
fun LoginForm(viewModel: LoginViewModel) {
    val email: String by viewModel.email.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState(initial = "")

    FieldTextComponent(
        labelValue = stringResource(R.string.email),
        icon = Icons.Default.Email,
        text = email,
        fieldType = FieldType.EMAIL,
    ) {
        viewModel.onLoginChanged(it, password)
    }

    FieldTextComponent(
        labelValue = stringResource(R.string.password),
        icon = Icons.Default.Lock,
        text = password,
        fieldType = FieldType.PASSWORD
    ) {
        viewModel.onLoginChanged(email, it)
    }
}

@Composable
fun ForgotPassword(modifier: Modifier) {
    Text(
        text = "Forgot Password?",
        style = MaterialTheme.typography.bodySmall,
        modifier = modifier.clickable { },
        color = MaterialTheme.colorScheme.primary,
        fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
    )
}

@Composable
fun DontHaveAccount(navController: NavController) {
    ClickableTextComponent(
        text = stringResource(R.string.dont_have_account),
        clickableWords = listOf("Sign", "Up"),
        onClick = {
            navController.navigate(AppScreens.RegisterScreen.routes)
        },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
            .padding(20.dp),
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    val navController = rememberNavController()
    val viewModel = LoginViewModel()
    LoginScreen(viewModel = viewModel, navController = navController)
}