package com.study.mybookshelf.ui.preferences

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.TimePicker
import androidx.preference.DialogPreference
import androidx.preference.PreferenceDialogFragmentCompat

class TimePickerPreferenceDialog : PreferenceDialogFragmentCompat() {

    private lateinit var timepicker: TimePicker

    override fun onCreateDialogView(context: Context?): View {
        timepicker = TimePicker(context)
        return timepicker
    }

    override fun onBindDialogView(view: View?) {
        super.onBindDialogView(view)

        val minutesAfterMidnight = (preference as TimePreference)
            .getPersistedMinutesFromMidnight()
        timepicker.setIs24HourView(true)
        timepicker.hour = minutesAfterMidnight / 60
        timepicker.minute = minutesAfterMidnight % 60
    }

    override fun onDialogClosed(positiveResult: Boolean) {
        // Save settings
        if(positiveResult) {
            val minutesAfterMidnight = (timepicker.hour * 60) + timepicker.minute
            (preference as TimePreference).persistMinutesFromMidnight(minutesAfterMidnight)

        }
    }

    companion object {
        fun newInstance(key: String): TimePickerPreferenceDialog {
            val fragment = TimePickerPreferenceDialog()
            val bundle = Bundle(1)
            bundle.putString(ARG_KEY, key)
            fragment.arguments = bundle

            return fragment
        }
    }
}


// This class is used in our preference where user can pick a time for notifications to appear.
// Specifically, this class is responsible for saving/retrieving preference data.
class TimePreference(context: Context?, attrs: AttributeSet?) : DialogPreference(context, attrs) {

    // Get saved preference value (in minutes from midnight, so 1 AM is represented as 1*60 here
    fun getPersistedMinutesFromMidnight(): Int {
        return super.getPersistedInt(DEFAULT_MINUTES_FROM_MIDNIGHT)
    }

    // Save preference
    fun persistMinutesFromMidnight(minutesFromMidnight: Int) {
        super.persistInt(minutesFromMidnight)
        notifyChanged()
    }

    override fun onSetInitialValue(defaultValue: Any?) {
        super.onSetInitialValue(defaultValue)
    }

    // Mostly for default values
    companion object {
        // By default we want notification to appear at 9 AM each time.
        private const val DEFAULT_HOUR = 9
        const val DEFAULT_MINUTES_FROM_MIDNIGHT = DEFAULT_HOUR * 60
    }

}
