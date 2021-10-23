package ru.bmstu.mobile.crypto.main

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.bmstu.mobile.crypto.model.Data

@Composable
fun CryptoList(
    values: State<Data?>,
    onItemSelected: (Int) -> Unit = {}
) {
    values.value?.data?.let {
        LazyColumn {
            items(it) { cryptoItem ->
                Item(
                    data = cryptoItem,
                    modifier = Modifier.fillMaxWidth().padding(8.dp)
                ) {
                    onItemSelected.invoke(it)
                }
            }
        }
    }
}
