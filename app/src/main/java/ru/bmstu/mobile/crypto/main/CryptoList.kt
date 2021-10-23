package ru.bmstu.mobile.crypto.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.bmstu.mobile.crypto.model.DataX
import ru.bmstu.mobile.crypto.network.LoadingState

@Composable
fun CryptoList(
    state: State<LoadingState?>,
    onItemSelected: (Int) -> Unit = {}
) {
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

@Composable
fun ListContent(
    content: List<DataX>,
    onItemSelected: (Int) -> Unit = {}
) {
    LazyColumn {
        items(content) { cryptoItem ->
            Item(
                data = cryptoItem,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                onItemSelected.invoke(it)
            }
        }
    }
}
