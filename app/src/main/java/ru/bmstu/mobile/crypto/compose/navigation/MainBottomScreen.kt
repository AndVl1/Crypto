package ru.bmstu.mobile.crypto.compose.navigation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import ru.bmstu.mobile.crypto.R

sealed class MainBottomScreen(val route: String, val icon: ImageVector?, @DrawableRes val res: Int?) {
    object History : MainBottomScreen("history", Icons.Filled.Home, null)
    object Settings : MainBottomScreen("settings", Icons.Filled.Settings, null)
    object Chart : MainBottomScreen("chart", null, R.drawable.outline_timeline_24)
}
