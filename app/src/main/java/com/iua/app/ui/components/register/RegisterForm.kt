package com.iua.app.ui.components.register

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.iua.app.R
import com.iua.app.ui.components.app.CheckBoxComponent
import com.iua.app.ui.components.app.FieldTextComponent
import com.iua.app.ui.components.app.FieldType
import com.iua.app.ui.navigation.AppScreens
import com.iua.app.ui.view_models.RegisterViewModel

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
            firstName,lastName, it, password, repeatPassword, checkBoxState
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
            firstName,  lastName, email, password, it, checkBoxState
        )
    }

    CheckBoxComponent(text = "I agree to the terms and conditions",
        clickableWords = listOf("terms", "conditions"),
        checkState = checkBoxState,
        onCheckChange = {
            viewModel.onRegisterChanged(
                firstName,lastName, email, password, repeatPassword, it
            )
        },
        onClick = {
            navController.navigate(AppScreens.TermsAndConditionsScreen.routes)
        })
}