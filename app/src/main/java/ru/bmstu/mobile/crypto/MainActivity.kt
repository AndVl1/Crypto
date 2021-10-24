package ru.bmstu.mobile.crypto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import ru.bmstu.mobile.crypto.chart.ChartScreen
import ru.bmstu.mobile.crypto.chart.ChartViewModel
import ru.bmstu.mobile.crypto.compose.navigation.MainBottomScreen
import ru.bmstu.mobile.crypto.compose.navigation.cryptoFlow
import ru.bmstu.mobile.crypto.compose.theme.CryptoCorners
import ru.bmstu.mobile.crypto.compose.theme.CryptoStyle
import ru.bmstu.mobile.crypto.compose.theme.CryptoTheme
import ru.bmstu.mobile.crypto.compose.theme.MainTheme
import ru.bmstu.mobile.crypto.compose.theme.baseDarkPalette
import ru.bmstu.mobile.crypto.compose.theme.baseLightPalette
import ru.bmstu.mobile.crypto.main.ListViewModel
import ru.bmstu.mobile.crypto.model.Currency
import ru.bmstu.mobile.crypto.settings.Settings
import ru.bmstu.mobile.crypto.settings.SettingsViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isDarkModeValue = isSystemInDarkTheme()

            val isDarkTheme = remember { mutableStateOf(isDarkModeValue) }
            val corners = remember { mutableStateOf(CryptoCorners.Rounded) }
            val style = remember { mutableStateOf(CryptoStyle.Purple) }
            val currency = remember { mutableStateOf(Currency.USD) }

            MainTheme(
                darkTheme = isDarkTheme.value,
                corners = corners.value,
                style = style.value
            ) {
                val navController = rememberNavController()
                val systemUiController = rememberSystemUiController()

                val navItems = listOf(
                    MainBottomScreen.History,
                    MainBottomScreen.Chart,
                    MainBottomScreen.Settings,
                )

                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = if (isDarkTheme.value) {
                            baseDarkPalette.primaryBackground
                        } else baseLightPalette.primaryBackground,
                        darkIcons = !isDarkTheme.value
                    )
                }
                Surface() {
                    Column {
                        Box(modifier = Modifier.weight(1f)) {
                            NavHost(
                                navController = navController, 
                                startDestination = MainBottomScreen.History.route
                            ) {
                                cryptoFlow(navController)

                                composable(MainBottomScreen.Settings.route){
                                    val settingsViewModel = hiltViewModel<SettingsViewModel>()
                                    Settings(
                                        viewModel = settingsViewModel,
                                        onDarkThereToggled = { isDarkTheme.value = it },
                                        darkTheme = isDarkTheme.value,
                                        onNewStyle = { style.value = it },
                                    )
                                }

                                composable(MainBottomScreen.Chart.route) {
                                    val viewModel = hiltViewModel<ChartViewModel>()
                                    ChartScreen(viewModel = viewModel)
                                }
                            }
                        }
                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                        ) {
                            BottomNavigation {
                                val navBackStackEntry by navController.currentBackStackEntryAsState()
                                val currentDestination = navBackStackEntry?.destination

                                navItems.forEach { screen ->
                                    val isSelected = currentDestination?.hierarchy
                                        ?.any{ it.route == screen.route } == true

                                    BottomNavigationItem(
                                        selected = isSelected,
                                        onClick = {
                                            navController.navigate(screen.route) {
                                                popUpTo(navController.graph.findStartDestination().id) {
                                                    saveState = true
                                                }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        },
                                        icon = {
                                            if (screen.icon != null) {
                                                Icon(
                                                    imageVector = screen.icon,
                                                    contentDescription = null,
                                                    tint = if (isSelected)
                                                        CryptoTheme.colors.tintColor
                                                    else
                                                        CryptoTheme.colors.controlColor
                                                )
                                            } else if (screen.res != null) {
                                                Icon(
                                                    painter = painterResource(id = screen.res),
                                                    contentDescription = null,
                                                    tint = if (isSelected)
                                                        CryptoTheme.colors.tintColor
                                                    else
                                                        CryptoTheme.colors.controlColor
                                                )
                                            }
                                        },
                                        modifier = Modifier.background(CryptoTheme.colors.primaryBackground),
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
