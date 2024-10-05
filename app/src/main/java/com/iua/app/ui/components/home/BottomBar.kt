package com.iua.app.ui.components.home

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.iua.app.ui.navigation.HomeSections

@Composable
fun BottomBar(homeNavController: NavHostController) {
    val screens = listOf(
        HomeSections.Home, HomeSections.Favorites
    )
    val navBackStackEntry by homeNavController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
    ) {
        screens.forEach { screen ->
            val isSelected = currentDestination?.route == screen.route
            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    homeNavController.navigate(screen.route) {
                        popUpTo(homeNavController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        modifier = Modifier.size(35.dp),
                        imageVector = screen.icon,
                        contentDescription = screen.label,
                        tint = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground
                    )
                },
                label = {
                    Text(text = screen.label, style = MaterialTheme.typography.labelLarge)
                },
                alwaysShowLabel = false, // Para que la etiqueta se muestre solo cuando está seleccionada
                modifier = Modifier.size(60.dp),
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.onBackground,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedTextColor = MaterialTheme.colorScheme.onBackground,
                    indicatorColor = Color.Transparent // Elimina el fondo gris
                )
            )
        }
    }
}