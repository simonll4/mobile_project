package com.iua.app.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.iua.app.presentation.auth.login.LoginScreen
import com.iua.app.presentation.auth.login.LoginViewModel
import com.iua.app.presentation.theme.AppTheme
import java.lang.reflect.Modifier


@Composable
fun HomeScreen(viewModel: LoginViewModel = hiltViewModel()) {
    Box(
        androidx.compose.ui.Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Home Screen")
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    AppTheme {
        HomeScreen()
    }
}
