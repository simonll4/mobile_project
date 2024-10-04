package com.iua.app.ui.components.login

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.iua.app.R
import com.iua.app.ui.components.app.ClickableTextComponent
import com.iua.app.ui.navigation.AppScreens

@Composable
fun DontHaveAccount(navController: NavController) {
    //Don't have an account? Sign Up
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