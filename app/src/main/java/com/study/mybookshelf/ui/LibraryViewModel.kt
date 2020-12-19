package com.study.mybookshelf.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.study.mybookshelf.R
import com.study.mybookshelf.Repository
import com.study.mybookshelf.model.Book
import com.study.mybookshelf.model.LendedBook
import com.study.mybookshelf.utils.BookType
import java.util.*
import kotlin.collections.ArrayList

class LibraryViewModel : ViewModel() {
    private val repository: Repository = Repository()
    val libraryBooksList : MutableLiveData<List<Book>> by lazy {
        MutableLiveData<List<Book>>(). apply {
            value = repository.getBooksList(BookType.BOOK)
        }
    }

}