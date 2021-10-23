package ru.bmstu.mobile.crypto.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.bmstu.mobile.crypto.R
import ru.bmstu.mobile.crypto.compose.custom.ClickableCard
import ru.bmstu.mobile.crypto.compose.theme.CryptoTheme
import ru.bmstu.mobile.crypto.extensions.toDate
import ru.bmstu.mobile.crypto.model.DataX

@Composable
@Preview
fun Item(
    modifier: Modifier = Modifier,
    data: DataX? = null,
    onClick: (Int) -> Unit = {}
) {
    data?.let {
        ClickableCard(
            elevation = 4.dp,
            modifier = modifier,
            onClick = { onClick.invoke(it.time) }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .focusable(true)
                    .padding(CryptoTheme.shape.padding)
                    .clickable { onClick.invoke(it.time) },
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column(verticalArrangement = Arrangement.Top) {
                    Text(text = "${stringResource(R.string.high_price)}: ${data.high}")
                    Text(text = "${stringResource(R.string.low_price)}: ${data.low}")
                    Text(text = "Open price: ${data.open}")
                    Text(text = "Close price: ${data.close}")
                }
                Text(text = "Closed at: ${data.time.toLong().toDate()}")
            }
        }
    }
}