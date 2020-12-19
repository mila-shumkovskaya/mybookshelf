package com.study.mybookshelf.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.study.mybookshelf.R
import com.study.mybookshelf.Repository
import com.study.mybookshelf.model.Book
import com.study.mybookshelf.model.LendedBook
import com.study.mybookshelf.utils.BookType
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

class LendedBooksViewModel : ViewModel() {
    private val repository: Repository = Repository()
    val lendedBooksList : MutableLiveData<List<Book>> by lazy {
        MutableLiveData<List<Book>>(). apply {
            value = repository.getBooksList(BookType.LENDED_BOOK)
        }
    }
}