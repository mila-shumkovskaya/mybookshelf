package com.study.mybookshelf.ui

import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Switch
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.study.mybookshelf.R
import kotlinx.android.synthetic.main.fragment_settings.*
import java.lang.Integer.parseInt
import java.time.LocalTime


class SettingsFragment : Fragment() {

    private var PRIVATE_MODE = 0
    private val PREF_NAME = "appSettings"
    private val PREF_SWITCH = "appSettingsSwitch"
    private val PREF_TIME_HOUR = "appSettingsTimeHour"
    private val PREF_TIME_MINUTE = "appSettingsTimeMinute"
    private val PREF_TIME_FORMAT = "appSettingsTimeFormat"


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_settings, container, false)
        val ntfTime: TimePicker = root.findViewById(R.id.tp_ntfTime)

        val ntfSwitch: Switch = root.findViewById(R.id.switchNotification)
        val sharedPref: SharedPreferences = requireContext().getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        val editor: Editor = sharedPref.edit()
        if(sharedPref.contains(PREF_TIME_HOUR)) {
            ntfTime.hour=sharedPref.getInt(PREF_TIME_HOUR, 12)
        }
        if(sharedPref.contains(PREF_TIME_MINUTE)) {
            ntfTime.minute=sharedPref.getInt(PREF_TIME_MINUTE, 12)
        }
        if(sharedPref.contains(PREF_TIME_FORMAT)) {
            ntfTime.setIs24HourView(sharedPref.getBoolean(PREF_TIME_FORMAT, false))
        }
        if(sharedPref.contains(PREF_SWITCH)) {
            ntfSwitch.isChecked=sharedPref.getBoolean(PREF_SWITCH, false)
        }

        ntfTime.setOnTimeChangedListener { _, _, _ ->
            editor.putInt(PREF_TIME_HOUR, ntfTime.hour)
            editor.putInt(PREF_TIME_MINUTE, ntfTime.minute)
            editor.putBoolean(PREF_TIME_FORMAT, ntfTime.is24HourView)
            editor.apply()
        }

         ntfSwitch.setOnCheckedChangeListener { _, isChecked ->
             editor.putBoolean(PREF_SWITCH, ntfSwitch.isChecked)
             editor.apply()
         }

        return root
    }
}