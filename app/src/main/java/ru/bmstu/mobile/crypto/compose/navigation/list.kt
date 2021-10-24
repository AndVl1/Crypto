package ru.bmstu.mobile.crypto.compose.navigation

import android.os.Bundle
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ru.bmstu.mobile.crypto.element.CryptoElement
import ru.bmstu.mobile.crypto.extensions.DataParamType
import ru.bmstu.mobile.crypto.main.CryptoList
import ru.bmstu.mobile.crypto.main.ListViewModel
import ru.bmstu.mobile.crypto.model.DataX

fun NavGraphBuilder.cryptoFlow(
    navController: NavController
) {
    navigation(startDestination = "list", route = MainBottomScreen.History.route) {
        composable("list") {
            val listViewModel = hiltViewModel<ListViewModel>()
            CryptoList(
                viewModel = listViewModel,
                onItemSelected = { data, cryptoCurrency, currency ->
                    val json = Json.encodeToString(data)
                    navController.navigate("item/$json/$currency/$cryptoCurrency")
                }
            )
        }
        composable(
            "item/{data}/{currency}/{crypto}",
            arguments = listOf(
                navArgument("data") { type = DataParamType() },
                navArgument("currency") { type = NavType.StringType },
                navArgument("crypto") { type = NavType.StringType },
            )
        ) {
            val data = it.arguments?.getParcelable<DataX>("data")
            val currency = it.arguments?.getString("currency")
            val cryptoCurrency = it.arguments?.getString("crypto")
            data?.let {
                CryptoElement(
                    data = data,
                    realCurrencyType = currency,
                    cryptoCurrencyType = cryptoCurrency,
                )
            }
        }
    }
}
