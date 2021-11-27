package ru.bmstu.mobile.crypto.network

import ru.bmstu.mobile.crypto.model.Data

sealed class ListScreenState {
    object Loading : ListScreenState()
    data class Loaded(val data: Data) : ListScreenState()
    object Error : ListScreenState()
}
