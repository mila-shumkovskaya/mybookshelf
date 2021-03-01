package com.study.mybookshelf.ui

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext

class SharedPreferencesId(context: Context) {
    private var PRIVATE_MODE = 0
    val PREF_NAME = "appSettings"
    private val PREF_ID = "savesID"
    private val sharedPref: SharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)

    fun getId() : Int{
        var id :Int = 29
        if(sharedPref.contains(PREF_ID)) {
            id=sharedPref.getInt(PREF_ID, 29)
        }
        return id
    }

    fun saveId(id: Int){
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putInt(PREF_ID, id)
        editor.apply()
    }
}