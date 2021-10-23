package ru.bmstu.mobile.crypto.element

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import ru.bmstu.mobile.crypto.R
import ru.bmstu.mobile.crypto.compose.theme.CryptoTheme
import ru.bmstu.mobile.crypto.extensions.toDate
import ru.bmstu.mobile.crypto.model.DataX

@Composable
fun CryptoElement(
    cryptoCurrencyType: State<String?>,
    realCurrencyType: State<String?>,
    data: DataX?
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = cryptoCurrencyType.value ?: "", color = CryptoTheme.colors.primaryText)
            Image(
                imageVector = Icons.Default.ArrowForward,
                null,
                colorFilter = ColorFilter.tint(CryptoTheme.colors.tintColor)
            )
            Text(text = realCurrencyType.value ?: "", color = CryptoTheme.colors.primaryText)
        }
        data?.let {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(CryptoTheme.shape.padding),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column(verticalArrangement = Arrangement.Top) {
                    Text(
                        text = "${stringResource(R.string.high_price)}: ${data.high}",
                        color = CryptoTheme.colors.primaryText
                    )
                    Text(
                        text = "${stringResource(R.string.low_price)}: ${data.low}",
                        color = CryptoTheme.colors.primaryText
                    )
                    Text(
                        text = "${stringResource(R.string.open_price)}: ${data.open}",
                        color = CryptoTheme.colors.primaryText
                    )
                    Text(
                        text = "${stringResource(R.string.close_price)}: ${data.close}",
                        color = CryptoTheme.colors.primaryText
                    )
                }
                Text(
                    text = "Closed at: ${data.time.toDate()}",
                    color = CryptoTheme.colors.primaryText
                )
            }
        }
    }
}
