package com.iua.app.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.iua.app.ui.components.home.BottomBar
import com.iua.app.ui.navigation.BottomBarNavigation
import com.iua.app.ui.view_models.HomeViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val homeNavController = rememberNavController()
    Scaffold(bottomBar = { BottomBar(homeNavController = homeNavController) }) {
        BottomBarNavigation(homeNavController = homeNavController)
    }
}

@Composable
fun HomeScreenContent() {
    Box {
        Text(text = "Home")
    }
}