package ru.bmstu.mobile.crypto.main

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
fun ListItem(
    modifier: Modifier = Modifier,
    data: DataX? = null,
    onClick: () -> Unit = {}
) {
    data?.let {
        ClickableCard(
            elevation = 4.dp,
            modifier = modifier,
            onClick = { onClick.invoke() }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .focusable(true)
                    .padding(CryptoTheme.shape.padding),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column(verticalArrangement = Arrangement.Top, modifier = Modifier.weight(1f)) {
                    Text(text = "${stringResource(R.string.high_price)}: ${data.high}")
                    Text(text = "${stringResource(R.string.low_price)}: ${data.low}")
                    Text(text = "${stringResource(R.string.open_price)}: ${data.openingTime}")
                    Text(text = "${stringResource(R.string.close_price)}: ${data.close}")
                }
                Text(text = "${stringResource(id = R.string.closed_at)}: ${data.time.toDate()}")
            }
        }
    }
}
