package ru.bmstu.mobile.crypto.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Shapes
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.bmstu.mobile.crypto.R
import ru.bmstu.mobile.crypto.model.DataX
import java.text.SimpleDateFormat
import java.util.*

@Composable
@Preview
fun Item(
    modifier: Modifier = Modifier,
    data: DataX? = null
) {
    Card (
        elevation = 4.dp,
        shape = RoundedCornerShape(4.dp),
        modifier = modifier,
    ) {
        data?.let {
            val date = SimpleDateFormat("dd-mm-yyyy", Locale.US)
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = it.time.toLong()
            date.calendar = calendar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(verticalArrangement = Arrangement.Top) {
                    Text(text = "${stringResource(R.string.high_price)}: ${data.high}")
                    Text(text = "${stringResource(R.string.low_price)}: ${data.low}")
                    Text(text = "Open price: ${data.open}")
                    Text(text = "Close price: ${data.close}")
                }
                Text(text = "Closed at: ${date.toPattern()}")
            }
        }
    }
}