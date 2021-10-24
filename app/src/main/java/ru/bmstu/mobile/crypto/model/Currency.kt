package ru.bmstu.mobile.crypto.model

enum class CryptoCurrency(val fullName: String) {
    BTC("Bitcoin"),
    ETH("Etherium"),
    BNB("Binance Coin"),
    DOGE("Dogecoin")
}

enum class Currency(val fullName: String) {
    EUR("Euro"),
    RUB("Ruble"),
    USD("US Dollar"),
}
