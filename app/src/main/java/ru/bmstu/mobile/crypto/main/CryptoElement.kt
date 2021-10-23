package ru.bmstu.mobile.crypto.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.bmstu.mobile.crypto.R
import ru.bmstu.mobile.crypto.model.DataX

@Composable
fun CryptoElement(
    cryptoCurrencyType: String,
    realCurrencyType: String,
    data: List<DataX>
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Absolute.SpaceBetween
    ) {
        Text(text = cryptoCurrencyType)
        Image(painterResource(R.drawable.arrow), "")
        Text(text = realCurrencyType)
    }
    LazyColumn{
        items(data) { item ->
            Item(
                data = item,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }
    }
}

@Composable
@Preview
fun ContentPreview() {
    CryptoElement(cryptoCurrencyType = "BTC", realCurrencyType = "USD", data = emptyList())
}