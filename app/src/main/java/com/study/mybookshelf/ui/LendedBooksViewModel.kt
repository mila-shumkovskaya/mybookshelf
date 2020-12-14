package com.study.mybookshelf.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LendedBooksViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Lended Books Fragment"
    }
    val text: LiveData<String> = _text
}