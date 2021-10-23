package ru.bmstu.mobile.crypto.settings

import android.os.Bundle
import androidx.core.widget.doAfterTextChanged
import androidx.preference.EditTextPreference
import androidx.preference.PreferenceFragmentCompat
import ru.bmstu.mobile.crypto.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
        findPreference<EditTextPreference>("days")?.setOnBindEditTextListener { editText ->
            editText.doAfterTextChanged { editable ->
                val value = editable.toString().toIntOrNull()
                if (value == null || value < 0) {
                    editText.error = "Wrong input"
                }
            }
        }
    }
}
