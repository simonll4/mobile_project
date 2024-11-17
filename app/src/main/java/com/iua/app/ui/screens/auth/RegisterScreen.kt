package com.iua.app.ui.screens.auth

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.iua.app.R
import androidx.navigation.NavController
import com.iua.app.ui.components.ButtonComponent
import com.iua.app.ui.components.CheckBoxComponent
import com.iua.app.ui.components.ClickableTextComponent
import com.iua.app.ui.components.FieldTextComponent
import com.iua.app.ui.components.FieldType
import com.iua.app.ui.components.NormalTextComponent
import com.iua.app.ui.components.TitleTextComponent
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
                coroutineScope.launch { viewModel.registerUser() }
            }
            OrDivider()
            AlreadyHaveAccountText(navController)
        }
    }
}

@Composable
fun Header() {
    NormalTextComponent(text = stringResource(R.string.welcome_to_register))
    TitleTextComponent(text = stringResource(R.string.create_account))
}

@Composable
fun RegisterForm(viewModel: RegisterViewModel, navController: NavController) {
    val firstName: String by viewModel.firstName.observeAsState(initial = "")
    val lastName: String by viewModel.lastName.observeAsState(initial = "")
    val email: String by viewModel.email.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState(initial = "")
    val repeatPassword: String by viewModel.repeatPassword.observeAsState(initial = "")
    val checkBoxState: Boolean by viewModel.checkBoxState.observeAsState(initial = false)

    FieldTextComponent(
        labelValue = stringResource(
            R.string.first_name,
        ), icon = Icons.Default.Person, text = firstName, fieldType = FieldType.TEXT
    ) {
        viewModel.onRegisterChanged(
            it, lastName, email, password, repeatPassword, checkBoxState
        )
    }

    FieldTextComponent(
        labelValue = stringResource(
            R.string.last_name,
        ), icon = Icons.Default.Person, text = lastName, fieldType = FieldType.TEXT
    ) {
        viewModel.onRegisterChanged(
            firstName, it, email, password, repeatPassword, checkBoxState
        )
    }

    FieldTextComponent(
        labelValue = stringResource(
            R.string.email,
        ), icon = Icons.Default.Email, text = email, fieldType = FieldType.EMAIL
    ) {
        viewModel.onRegisterChanged(
            firstName, lastName, it, password, repeatPassword, checkBoxState
        )
    }

    FieldTextComponent(
        labelValue = stringResource(
            R.string.password,
        ), icon = Icons.Default.Lock, text = password, fieldType = FieldType.PASSWORD
    ) {
        viewModel.onRegisterChanged(
            firstName, lastName, email, it, repeatPassword, checkBoxState
        )
    }

    FieldTextComponent(
        labelValue = stringResource(
            R.string.repeat_password,
        ), icon = Icons.Default.Lock, text = repeatPassword, fieldType = FieldType.PASSWORD
    ) {
        viewModel.onRegisterChanged(
            firstName, lastName, email, password, it, checkBoxState
        )
    }

    CheckBoxComponent(text = stringResource(R.string.accept_terms_and_conditions),
        clickableWords = listOf("terms", "and", "conditions"),
        checkState = checkBoxState,
        onCheckChange = {
            viewModel.onRegisterChanged(
                firstName, lastName, email, password, repeatPassword, it
            )
        },
        onClick = {
            navController.navigate(AppScreens.TermsAndConditionsScreen.routes)
        })
}

@Composable
fun OrDivider() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 25.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier
            .weight(1f)
            .padding(10.dp)) {
            HorizontalLine()
        }

        Text(
            text = "or",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        Box(modifier = Modifier
            .weight(1f)
            .padding(10.dp)) {
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp),
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f)
            )
        }
    }
}

@Composable
fun HorizontalLine(modifier: Modifier = Modifier) {
    HorizontalDivider(
        modifier = modifier
            .fillMaxWidth()
            .height(1.dp),
        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f)
    )
}

@Composable
fun AlreadyHaveAccountText(navController: NavController) {
    ClickableTextComponent(
        text = stringResource(R.string.already_have_an_account),
        clickableWords = listOf("Login"),
        onClick = {
            navController.navigate(AppScreens.LoginScreen.routes)
        },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
    )
}

