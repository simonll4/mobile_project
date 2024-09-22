package com.iua.app.ui.components.register

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.iua.app.R
import com.iua.app.ui.components.app.ClickableTextComponent
import com.iua.app.ui.navigation.AppScreens

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