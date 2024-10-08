package com.iua.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.iua.app.R
import com.iua.app.ui.components.TopAppBarComponent

@Composable
fun TermsAndConditionsScreen(navController: NavHostController) {

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        topBar = {
            TopAppBarComponent(
                navController = navController,
                text = stringResource(R.string.terms_and_conditions_top_bar),
                Icons.AutoMirrored.Filled.ArrowBack
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp)
                    .align(Alignment.TopCenter)
            ) {
                // Add the terms and conditions text here
            }
        }
    }
}