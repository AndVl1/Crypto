package ru.bmstu.mobile.crypto.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.bmstu.mobile.crypto.compose.custom.ClickableCard
import ru.bmstu.mobile.crypto.compose.theme.CryptoTheme
import ru.bmstu.mobile.crypto.model.CryptoCurrency
import ru.bmstu.mobile.crypto.model.DataX
import ru.bmstu.mobile.crypto.network.LoadingState

@Composable
fun CryptoList(
    defCryptoCurrency: CryptoCurrency,
    state: State<LoadingState?>,
    onItemSelected: (DataX) -> Unit = {},
    onCurrencySelected: (CryptoCurrency) -> Unit = {}
) {
    val selectedCurrency = remember() {
        mutableStateOf(defCryptoCurrency)
    }
    val menuExpanded = remember {
        mutableStateOf(false)
    }

    Column(modifier = Modifier.fillMaxSize()) {

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
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
                        text = selectedCurrency.value.fullName,
                        modifier = Modifier,
                        textAlign = TextAlign.Center
                    )
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
                }
            }
            Button(
                modifier = Modifier
                    .wrapContentWidth(),
                onClick = { onCurrencySelected.invoke(selectedCurrency.value) },
            ) {
                Text(text = "Submit")
            }

            DropdownMenu(
                expanded = menuExpanded.value,
                onDismissRequest = { menuExpanded.value = false },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(CryptoTheme.colors.secondaryBackground),
            ) {
                CryptoCurrency.values().forEach {
                    DropdownMenuItem(onClick = {
                        selectedCurrency.value = it
                        menuExpanded.value = false
                    }) {
                        Text(
                            text = it.fullName,
                            color = CryptoTheme.colors.primaryText
                        )
                    }
                }
            }
        }

        when (state.value) {
            is LoadingState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is LoadingState.Error -> {
                ErrorScreen()
            }
            is LoadingState.Loaded -> {
                ListContent(
                    content = (state.value as LoadingState.Loaded).data.data,
                    onItemSelected = onItemSelected
                )
            }
        }
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
                onClick = { onItemSelected.invoke(cryptoItem) }
            )
        }
    }
}
