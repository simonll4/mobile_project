package com.iua.app.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.iua.app.ui.view_models.ThemeViewModel

private val DarkColorScheme = darkColorScheme(
    primary = LightBlueAccent4,
    secondary = LightBlueAccent3,
    tertiary = LightBlueAccent2,
    surface = DarkGrey,
    background = Black,
    onPrimary = White,
    onSecondary = White,
    onTertiary = White,
    onBackground = White,
    onSurface = White,
    primaryContainer = GreyContainerBlack,
    secondaryContainer = GreyContainerBlack
)

private val LightColorScheme = lightColorScheme(
    primary = LightBlueAccent3,
    secondary = LightBlueAccent1,
    tertiary = LightBlueAccent2,
    background = Color(0xFFFFFBFE),
    surface = Grey,
    onPrimary = Black,
    onSecondary = Black,
    onTertiary = Black,
    onBackground = Black,
    onSurface = Color(0xFF1C1B1F),
    primaryContainer = White,
    secondaryContainer = White
)

@Composable
fun MyApplicationTheme(
    themeViewModel: ThemeViewModel = hiltViewModel(),
    content: @Composable () -> Unit
) {
    val isDarkMode by themeViewModel.isDarkMode.collectAsState(initial = false)

    val colorScheme = if (isDarkMode) DarkColorScheme else LightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        LaunchedEffect(isDarkMode) {
            (view.context as Activity).window.statusBarColor = colorScheme.primary.toArgb()
            ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = !isDarkMode
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}