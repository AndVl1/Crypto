package ru.bmstu.mobile.crypto.element

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement.Absolute.SpaceBetween
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.TextField
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.bmstu.mobile.crypto.R
import ru.bmstu.mobile.crypto.compose.theme.MainTheme
import ru.bmstu.mobile.crypto.model.DataX


@AndroidEntryPoint
class CryptoElementFragment : Fragment() {

    private val viewModel: ElementViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val data = arguments?.getSerializable("data") as? DataX
        return ComposeView(requireContext()).apply {
            setContent {
                val data = arguments?.getSerializable("data") as? DataX
                MainTheme {
                    SideEffect {
                        viewModel.init()
                    }
                    CryptoElement(
                        cryptoCurrencyType = viewModel.cryptoCurrency.collectAsState(""),
                        realCurrencyType = viewModel.realCurrency.collectAsState(""),
                        data = data
                    )
                }
            }
        }
    }
}