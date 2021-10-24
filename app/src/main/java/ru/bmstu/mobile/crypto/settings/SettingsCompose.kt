package ru.bmstu.mobile.crypto.settings

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.bmstu.mobile.crypto.R
import ru.bmstu.mobile.crypto.compose.custom.ColorCard
import ru.bmstu.mobile.crypto.compose.custom.MenuItem
import ru.bmstu.mobile.crypto.compose.custom.MenuItemModel
import ru.bmstu.mobile.crypto.compose.theme.CryptoStyle
import ru.bmstu.mobile.crypto.compose.theme.CryptoTheme
import ru.bmstu.mobile.crypto.compose.theme.blueDarkPalette
import ru.bmstu.mobile.crypto.compose.theme.blueLightPalette
import ru.bmstu.mobile.crypto.compose.theme.greenDarkPalette
import ru.bmstu.mobile.crypto.compose.theme.greenLightPalette
import ru.bmstu.mobile.crypto.compose.theme.orangeDarkPalette
import ru.bmstu.mobile.crypto.compose.theme.orangeLightPalette
import ru.bmstu.mobile.crypto.compose.theme.purpleDarkPalette
import ru.bmstu.mobile.crypto.compose.theme.purpleLightPalette
import ru.bmstu.mobile.crypto.compose.theme.redDarkPalette
import ru.bmstu.mobile.crypto.compose.theme.redLightPalette
import ru.bmstu.mobile.crypto.model.Currency

@Composable
fun Settings(
    viewModel: SettingsViewModel,
    onDarkThemeToggled: (Boolean) -> Unit,
    darkTheme: Boolean = false,
    onNewStyle: (CryptoStyle) -> Unit,
) {
    val selectedCurrency = remember {
        mutableStateOf(viewModel.getCurrency())
    }
    val selectedDays = remember {
        mutableStateOf(viewModel.getDays())
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = CryptoTheme.colors.primaryBackground,
    ) {
        Column {
            TopAppBar(
                backgroundColor = CryptoTheme.colors.primaryBackground,
                elevation = 8.dp
            ) {
                Text(
                    text = stringResource(R.string.settings),
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = CryptoTheme.shape.padding),
                    color = CryptoTheme.colors.primaryText,
                    style = CryptoTheme.typography.toolbar,
                )
            }
            Text(
                text = stringResource(id = R.string.common_header),
                style = CryptoTheme.typography.heading,
                modifier = Modifier.padding(start = 16.dp)
            )
            MenuItem(
                model = MenuItemModel(
                    title = stringResource(id = R.string.currency_title),
                    values = Currency.values().map { value -> value.fullName },
                    currentIndex = Currency.values().indexOf(selectedCurrency.value)
                ),
                onItemSelected = { index ->
                    selectedCurrency.value = Currency.values()[index]
                    viewModel.updateCurrency(selectedCurrency.value)
                }
            )
            MenuItem(
                model = MenuItemModel(
                    title = stringResource(id = R.string.days_title),
                    values = (1..30).toList().map { it.toString() },
                    currentIndex = selectedDays.value - 1
                ),
                onItemSelected = { index ->
                    selectedDays.value = index + 1
                    viewModel.updateDays(selectedDays.value)
                }
            )
            Text(
                text = stringResource(id = R.string.ui),
                style = CryptoTheme.typography.heading,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp)
            )
            Card(
                modifier = Modifier
                    .padding(horizontal = CryptoTheme.shape.padding),
                backgroundColor = CryptoTheme.colors.secondaryBackground,
                shape = CryptoTheme.shape.cornerStyle
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(CryptoTheme.shape.padding)
                ) {
                    Text(text = "Dark theme", style = CryptoTheme.typography.body)
                    Checkbox(
                        checked = darkTheme,
                        onCheckedChange = {
                            onDarkThemeToggled.invoke(it)
                        },
                        colors = CheckboxDefaults.colors(
                            checkedColor = CryptoTheme.colors.tintColor,
                            uncheckedColor = CryptoTheme.colors.secondaryText
                        )
                    )
                }
            }
            Row(
                modifier = Modifier
                    .padding(CryptoTheme.shape.padding)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ColorCard(
                    color = if (darkTheme) blueDarkPalette.tintColor else blueLightPalette.tintColor,
                    onClick = { onNewStyle.invoke(CryptoStyle.Blue) }
                )
                ColorCard(
                    color = if (darkTheme) purpleDarkPalette.tintColor else purpleLightPalette.tintColor,
                    onClick = { onNewStyle.invoke(CryptoStyle.Purple) }
                )
                ColorCard(
                    color = if (darkTheme) orangeDarkPalette.tintColor else orangeLightPalette.tintColor,
                    onClick = { onNewStyle.invoke(CryptoStyle.Orange) }
                )
            }
            Row(
                modifier = Modifier
                    .padding(CryptoTheme.shape.padding)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ColorCard(
                    color = if (darkTheme) greenDarkPalette.tintColor else greenLightPalette.tintColor,
                    onClick = { onNewStyle.invoke(CryptoStyle.Green) }
                )
                ColorCard(
                    color = if (darkTheme) redDarkPalette.tintColor else redLightPalette.tintColor,
                    onClick = { onNewStyle.invoke(CryptoStyle.Red) }
                )
            }
        }
    }
}
