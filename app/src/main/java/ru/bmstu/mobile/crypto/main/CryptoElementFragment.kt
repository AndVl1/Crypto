package ru.bmstu.mobile.crypto.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement.Absolute.SpaceBetween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.TextField
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.bmstu.mobile.crypto.R
import ru.bmstu.mobile.crypto.network.LoadingState


@AndroidEntryPoint
class CryptoElementFragment : Fragment() {

    private val viewModel: ElementViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.init()
        return ComposeView(requireContext()).apply {
            setContent {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = SpaceBetween) {
                    TextField(value = viewModel.cryptoCurrency, onValueChange = { })
                    Image(painterResource(R.drawable.arrow),"")
                    TextField(value = viewModel.realCurrency, onValueChange = { })
                }
            }
        }
    }
}