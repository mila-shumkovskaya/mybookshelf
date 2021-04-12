package com.study.mybookshelf.ui.fragments

import android.os.Bundle
import android.widget.Toast
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import com.study.mybookshelf.R
import com.study.mybookshelf.ui.preferences.TimePickerPreferenceDialog
import com.study.mybookshelf.ui.preferences.TimePreference

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        val switchNotifications: SwitchPreferenceCompat? = findPreference("enable_notifications")
        switchNotifications?.setOnPreferenceChangeListener { _, newValue ->
            if (newValue == true) {
                Toast.makeText(activity, R.string.notifications_enabled, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(activity, R.string.notifications_disabled, Toast.LENGTH_LONG).show()
            }
            true
        }
    }

    override fun onDisplayPreferenceDialog(preference: Preference?) {
        if(preference is TimePreference) {
            val timepickerdialog = TimePickerPreferenceDialog.newInstance(preference.key)
            timepickerdialog.setTargetFragment(this, 0)
            timepickerdialog.show(parentFragmentManager, "time_picker_dialog")
        }
        else {
            super.onDisplayPreferenceDialog(preference)
        }
    }
}