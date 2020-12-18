package com.study.mybookshelf.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.study.mybookshelf.R
import com.study.mybookshelf.model.BorrowedBook
import java.util.*
import kotlin.collections.ArrayList


class BorrowedBooksViewModel : ViewModel() {

    var borrowedBooksList : MutableLiveData<List<BorrowedBook>> = MutableLiveData()

    init {
        val booksList = ArrayList<BorrowedBook>()
        booksList.add(BorrowedBook("HarryPotter1", "J.K.Rowling", R.mipmap.ic_launcher, 5.0, true, "comment1", "Dasha", Date(2020, 12, 12), Date(2020, 12, 12)))
        booksList.add(BorrowedBook("HarryPotter2", "J.K.Rowling", R.mipmap.ic_launcher, 5.0, true, "comment1", "Dasha", Date(2020, 12, 12), Date(2020, 12, 12)))
        booksList.add(BorrowedBook("HarryPotter3", "J.K.Rowling", R.mipmap.ic_launcher, 5.0, true, "comment1", "Dasha", Date(2020, 12, 12), Date(2020, 12, 12)))
        booksList.add(BorrowedBook("HarryPotter4", "J.K.Rowling", R.mipmap.ic_launcher, 5.0, true, "comment1", "Dasha", Date(2020, 12, 12), Date(2020, 12, 12)))
        booksList.add(BorrowedBook("HarryPotter5", "J.K.Rowling", R.mipmap.ic_launcher, 5.0, true, "comment1", "Dasha", Date(2020, 12, 12), Date(2020, 12, 12)))
        booksList.add(BorrowedBook("HarryPotter6", "J.K.Rowling", R.mipmap.ic_launcher, 5.0, true, "comment1", "Dasha", Date(2020, 12, 12), Date(2020, 12, 12)))
        booksList.add(BorrowedBook("HarryPotter7", "J.K.Rowling", R.mipmap.ic_launcher, 5.0, true, "comment1", "Dasha", Date(2020, 12, 12), Date(2020, 12, 12)))
        borrowedBooksList.value = booksList
    }
}