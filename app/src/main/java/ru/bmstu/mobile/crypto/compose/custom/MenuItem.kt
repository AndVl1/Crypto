package ru.bmstu.mobile.crypto.compose.custom

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.bmstu.mobile.crypto.compose.theme.CryptoTheme

data class MenuItemModel(
    val title: String,
    val currentIndex: Int = 0,
    val values: List<String>
)

@Composable
fun MenuItem(
    model: MenuItemModel,
    onItemSelected: (Int) -> Unit = {}
) {
    val isDropDownOpen = remember {
        mutableStateOf(false)
    }
    val currentPosition = remember {
        mutableStateOf(model.currentIndex)
    }

    Box(
        modifier = Modifier.fillMaxWidth()
            .background(CryptoTheme.colors.primaryBackground)
    ) {
        Row(
            modifier = Modifier
                .padding(CryptoTheme.shape.padding)
                .clickable { isDropDownOpen.value = true },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = model.title,
                style = CryptoTheme.typography.body,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = model.values[currentPosition.value],
                style = CryptoTheme.typography.body,
                color = CryptoTheme.colors.secondaryText
            )
            Icon(
                imageVector = Icons.Outlined.KeyboardArrowRight,
                contentDescription = null,
                tint = CryptoTheme.colors.secondaryText
            )
        }

        DropdownMenu(
            expanded = isDropDownOpen.value,
            onDismissRequest = { isDropDownOpen.value = false },
            modifier = Modifier
                .background(CryptoTheme.colors.secondaryBackground)
                .fillMaxWidth()
                .padding(horizontal = CryptoTheme.shape.padding)
        ) {
            model.values.forEachIndexed { index, value ->
                DropdownMenuItem(
                    onClick = {
                        onItemSelected.invoke(index)
                        currentPosition.value = index
                        isDropDownOpen.value = false
                    },
                ) {
                    Column() {
                        Text(
                            text = value,
                            style = CryptoTheme.typography.body
                        )
                        Divider(color = CryptoTheme.colors.primaryBackground)
                    }
                }
            }
        }
        Divider(
            modifier = Modifier.padding(start = CryptoTheme.shape.padding).align(Alignment.BottomStart)
        )
    }
}