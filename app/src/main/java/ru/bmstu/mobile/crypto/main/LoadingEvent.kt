package ru.bmstu.mobile.crypto.main

import ru.bmstu.mobile.crypto.model.CryptoCurrency
import ru.bmstu.mobile.crypto.model.DataX

sealed class LoadingEvent {
    object EnterScreen : LoadingEvent()
    data class ReloadScreen(val currency: CryptoCurrency) : LoadingEvent()
    data class ItemClickedCallback(val item: DataX) : LoadingEvent()
}
