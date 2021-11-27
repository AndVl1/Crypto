package ru.bmstu.mobile.crypto.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import ru.bmstu.mobile.crypto.R
import ru.bmstu.mobile.crypto.compose.custom.ClickableCard
import ru.bmstu.mobile.crypto.compose.custom.MenuItem
import ru.bmstu.mobile.crypto.compose.custom.MenuItemModel
import ru.bmstu.mobile.crypto.compose.theme.CryptoTheme
import ru.bmstu.mobile.crypto.extensions.openLink
import ru.bmstu.mobile.crypto.model.CryptoCurrency
import ru.bmstu.mobile.crypto.model.DataX
import ru.bmstu.mobile.crypto.network.ListScreenState

@Composable
fun CryptoList(
    viewModel: ListViewModel,
    onItemSelected: ((DataX, String, String) -> Unit)? = null,
) {
    val state = viewModel.cryptoHistory.collectAsState(ListScreenState.Loading)
    val context = LocalContext.current

    val selectedCryptoCurrency = viewModel.cryptoCurrency.collectAsState()

    val menuExpanded = remember {
        mutableStateOf(false)
    }
    Surface(color = CryptoTheme.colors.primaryBackground) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                MenuItem(
                    model = MenuItemModel(
                        title = stringResource(id = R.string.currency_title),
                        values = CryptoCurrency.values().map { currency -> currency.fullName },
                        currentIndex = CryptoCurrency.values().indexOf(selectedCryptoCurrency.value)
                    ),
                    onItemSelected = { index ->
                        viewModel.handleIntent(LoadingEvent.ReloadScreen(CryptoCurrency.values()[index]))
                    }
                )
                ClickableCard(
                    modifier = Modifier
                        .height(52.dp)
                        .width(144.dp)
                        .padding(8.dp),
                    onClick = { menuExpanded.value = true },
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.padding(CryptoTheme.shape.padding)
                    ) {
                        Text(
                            text = selectedCryptoCurrency.value.fullName,
                            style = CryptoTheme.typography.body,
                            textAlign = TextAlign.Center
                        )
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = null,
                            tint = CryptoTheme.colors.primaryText
                        )
                    }
                }
            }
            ClickableText(
                text = AnnotatedString(
                    text = "https://min-api.cryptocompare.com/documentation",
                    spanStyle = SpanStyle(
                        color = Color.Blue,
                        textDecoration = TextDecoration.Underline
                    )
                ),
                onClick = { context.openLink("https://min-api.cryptocompare.com/documentation") }
            )
            SwipeRefresh(
                state = rememberSwipeRefreshState(isRefreshing = state.value == ListScreenState.Loading),
                onRefresh = { viewModel.handleIntent(LoadingEvent.ReloadScreen(selectedCryptoCurrency.value)) }
            ) {
                when (state.value) {
                    is ListScreenState.Loading -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(color = CryptoTheme.colors.tintColor)
                        }
                    }
                    is ListScreenState.Error -> {
                        ErrorScreen()
                    }
                    is ListScreenState.Loaded -> {
                        ListContent(
                            content = (state.value as ListScreenState.Loaded).data.values,
                            onItemSelected = { data ->
                                onItemSelected?.invoke(
                                    data,
                                    selectedCryptoCurrency.value.name,
                                    viewModel.currency.value.name
                                )
                            }
                        )
                    }
                }
            }
        }
    }

    LaunchedEffect(state) {
        viewModel.handleIntent(LoadingEvent.EnterScreen)
    }
}

@Composable
fun ListContent(
    content: List<DataX>,
    onItemSelected: (DataX) -> Unit = {}
) {
    LazyColumn {
        items(content) { cryptoItem ->
            ListItem(
                data = cryptoItem,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onClick = { onItemSelected(cryptoItem) }
            )
        }
    }
}
