package ru.bmstu.mobile.crypto.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import ru.bmstu.mobile.crypto.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}
