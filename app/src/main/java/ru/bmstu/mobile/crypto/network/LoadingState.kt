package ru.bmstu.mobile.crypto.network

import ru.bmstu.mobile.crypto.model.Data

sealed class LoadingState {
    object Loading : LoadingState()
    data class Loaded(val data: Data) : LoadingState()
    object Error : LoadingState()
}
