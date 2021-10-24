package ru.bmstu.mobile.crypto.compose.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

sealed class MainBottomScreen(val route: String, val icon: ImageVector) {
    object History : MainBottomScreen("history", Icons.Filled.Home)
    object Settings : MainBottomScreen("settings", Icons.Filled.Settings)
}
