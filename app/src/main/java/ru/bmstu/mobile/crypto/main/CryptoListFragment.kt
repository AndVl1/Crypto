package ru.bmstu.mobile.crypto.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.bmstu.mobile.crypto.R
import ru.bmstu.mobile.crypto.model.CryptoCurrency
import ru.bmstu.mobile.crypto.network.LoadingState

@AndroidEntryPoint
class CryptoListFragment : Fragment() {

    private val viewModel: ListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.init()
        return ComposeView(requireContext()).apply {
            setContent {
                CryptoList(
                    state = viewModel.cryptoHistory.collectAsState(initial = LoadingState.Loading),
                    onItemSelected = { time -> onItemSelected(time) },
                    onCurrencySelected = { currency -> onCryptoCurrencySelected(currency) }
                )
            }
        }
    }

    private fun onItemSelected(time: Int) {
        // findNavController().navigate(...)
    }

    private fun onCryptoCurrencySelected(currency: CryptoCurrency) {
        viewModel.update(currency)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.settings -> {
                findNavController().navigate(R.id.settingsFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
