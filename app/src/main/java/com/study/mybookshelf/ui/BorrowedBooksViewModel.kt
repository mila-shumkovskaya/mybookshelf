package com.study.mybookshelf.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class BorrowedBooksViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Borrowed Books Fragment"
    }
    val text: LiveData<String> = _text
}