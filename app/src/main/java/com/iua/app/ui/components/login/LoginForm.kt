package com.iua.app.ui.components.login

import androidx.compose.runtime.Composable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.stringResource
import com.iua.app.R
import com.iua.app.ui.components.app.FieldTextComponent
import com.iua.app.ui.components.app.FieldType
import com.iua.app.ui.view_models.LoginViewModel

@Composable
fun LoginForm(viewModel: LoginViewModel) {
    val email: String by viewModel.email.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState(initial = "")

    FieldTextComponent(
        labelValue = stringResource(R.string.email),
        icon = Icons.Default.Email,
        text = email,
        fieldType = FieldType.EMAIL
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

