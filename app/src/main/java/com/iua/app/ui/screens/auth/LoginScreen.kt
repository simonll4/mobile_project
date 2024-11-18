package com.iua.app.ui.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.iua.app.R
import androidx.hilt.navigation.compose.hiltViewModel
import com.iua.app.ui.components.ButtonComponent
import com.iua.app.ui.components.ClickableTextComponent
import com.iua.app.ui.components.FieldTextComponent
import com.iua.app.ui.components.FieldType
import com.iua.app.ui.view_models.LoginViewModel
import com.iua.app.ui.navigation.AppScreens

// todo cuando se hace el login mal mete un parpadeo, no deberia pasar. y mejorar tranisicon a home

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navController: NavController
) {
    val isLoginSuccessful: Boolean by viewModel.isLoginSuccessful.observeAsState(initial = false)
    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)
    val loginError: String? by viewModel.loginError.observeAsState(initial = null)

    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp), contentAlignment = Alignment.Center
    ) {
        when {
            isLoading -> {
                //CircularProgressIndicator(Modifier.align(Alignment.Center))
            }

            isLoginSuccessful -> {
                LaunchedEffect(Unit) {
                    navController.navigate(AppScreens.HomeScreen.routes) {
                        popUpTo(0) { inclusive = true } // Vacía toda la pila de navegación
                    }
                }
            }

            else -> {
                LoginContent(
                    viewModel = viewModel,
                    navController = navController,
                    loginError = loginError
                )
            }
        }
    }
}

@Composable
fun LoginContent(
    viewModel: LoginViewModel,
    navController: NavController,
    loginError: String?
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Imagen de encabezado
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

        Spacer(modifier = Modifier.height(16.dp))

        // Formulario de inicio de sesión
        LoginForm(viewModel)

        // Link de "Olvidé mi contraseña"
        ForgotPassword(
            modifier = Modifier
                .align(Alignment.End)
                .padding(top = 8.dp, bottom = 16.dp)
        )

        // Botón de iniciar sesión
        val loginEnable: Boolean by viewModel.loginEnable.observeAsState(initial = false)
        ButtonComponent(
            text = stringResource(R.string.login_button),
            enabled = loginEnable
        ) {
            viewModel.onButtonSelected()
        }

        // Aislar el mensaje de error
        Spacer(modifier = Modifier.height(8.dp))

        ErrorMessage(loginError)

        Spacer(modifier = Modifier.height(16.dp))

        // Link para registrarse
        DontHaveAccount(navController)
    }
}

@Composable
fun ErrorMessage(error: String?) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .height(20.dp),
        contentAlignment = Alignment.Center
    ) {
        key(error) { // Aislar cambios de este estado
            error?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
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