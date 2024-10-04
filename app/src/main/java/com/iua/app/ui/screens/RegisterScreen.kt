package com.iua.app.ui.screens


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.iua.app.R
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.iua.app.ui.components.app.ButtonComponent
import com.iua.app.ui.components.register.AlreadyHaveAccountText
import com.iua.app.ui.components.register.Header
import com.iua.app.ui.components.register.OrDivider
import com.iua.app.ui.components.register.RegisterForm
import com.iua.app.ui.navigation.AppScreens
import com.iua.app.ui.view_models.RegisterViewModel
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(viewModel: RegisterViewModel = hiltViewModel(), navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Register(Modifier.align(Alignment.Center), viewModel, navController)
    }
}

@Composable
fun Register(modifier: Modifier, viewModel: RegisterViewModel, navController: NavController) {
    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)
    val isRegisterSuccessful: Boolean by viewModel.isRegisterSuccessful.observeAsState(initial = false)
    val registerEnable: Boolean by viewModel.registerEnable.observeAsState(initial = false)
    val coroutineScope = rememberCoroutineScope()

    if (isLoading) {
        Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    } else if (isRegisterSuccessful) {
        LaunchedEffect(Unit) {
            navController.navigate(AppScreens.HomeScreen.routes) {
                popUpTo(0) { inclusive = true } // Esto vacía toda la pila.
            }
        }
    } else {
        Column(modifier = modifier.padding(10.dp)) {
            Header()
            RegisterForm(viewModel, navController)
            ButtonComponent(
                text = stringResource(
                    R.string.register_button,
                ), enabled = registerEnable
            ) {
                print("RegisterButton: onButtonSelected")
                coroutineScope.launch { viewModel.onButtonSelected() }
            }
            OrDivider()
            AlreadyHaveAccountText(navController)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewRegisterScreen() {
    val navController = rememberNavController()
    val viewModel = RegisterViewModel()
    RegisterScreen(viewModel = viewModel, navController = navController)
}

