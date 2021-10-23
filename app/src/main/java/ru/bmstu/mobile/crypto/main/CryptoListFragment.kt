package ru.bmstu.mobile.crypto.main

import android.content.Context
import android.content.SharedPreferences
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
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceManager
import by.kirich1409.viewbindingdelegate.internal.findRootView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.filter
import org.joda.time.DateTime
import ru.bmstu.mobile.crypto.R
import java.sql.Date
import java.time.Duration
import java.time.LocalDate
import java.util.*

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
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val currency = preferences.getString("currency", "").toString()
        val days = preferences.getString("days", "")?.toInt()
        viewModel.init("BTC", currency)
        return ComposeView(requireContext()).apply {
            setContent {
                CryptoList(
                    values = viewModel.cryptoHistory.filter { elem ->
                        requireNotNull(elem)
                        if (days != null) {
                            val minDate = DateTime.now().minus(days.toLong())
                            minDate.isBefore(elem.timeFrom.toLong())
                        }
                        true
                    }.collectAsState(initial = null),
                    onItemSelected = { time -> onItemSelected(time) }
                )
            }
        }
    }

    private fun onItemSelected(time: Int) {
        // findNavController().navigate(...)
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
