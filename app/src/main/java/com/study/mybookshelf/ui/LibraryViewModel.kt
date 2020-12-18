package com.study.mybookshelf.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.study.mybookshelf.R
import com.study.mybookshelf.model.Book
import com.study.mybookshelf.model.LendedBook
import java.util.*
import kotlin.collections.ArrayList

class LibraryViewModel : ViewModel() {

    var libraryBooksList : MutableLiveData<List<Book>> = MutableLiveData()

    init {
        val booksList = ArrayList<Book>()
        booksList.add(Book("HarryPotter1", "J.K.Rowling", R.mipmap.ic_launcher, 5.0, true, "comment1"))
        booksList.add(Book("HarryPotter2", "J.K.Rowling", R.mipmap.ic_launcher, 5.0, true, "comment1"))
        booksList.add(Book("HarryPotter3", "J.K.Rowling", R.mipmap.ic_launcher, 5.0, true, "comment1"))
        booksList.add(Book("HarryPotter4", "J.K.Rowling", R.mipmap.ic_launcher, 5.0, true, "comment1"))
        booksList.add(Book("HarryPotter5", "J.K.Rowling", R.mipmap.ic_launcher, 5.0, true, "comment1"))
        booksList.add(Book("HarryPotter6", "J.K.Rowling", R.mipmap.ic_launcher, 5.0, true, "comment1"))
        booksList.add(Book("HarryPotter7", "J.K.Rowling", R.mipmap.ic_launcher, 5.0, true, "comment1"))
        libraryBooksList.value = booksList
    }
}