package com.study.mybookshelf.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.study.mybookshelf.R
import com.study.mybookshelf.model.BorrowedBook
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList


@RequiresApi(Build.VERSION_CODES.O)
class BorrowedBooksViewModel : ViewModel() {

    var borrowedBooksList : MutableLiveData<List<BorrowedBook>> = MutableLiveData()

    init {
        val booksList = ArrayList<BorrowedBook>()
        booksList.add(BorrowedBook("HarryPotter1", "J.K.Rowling", R.mipmap.ic_launcher, 5.0.toFloat(), true, "comment1", "Dasha", LocalDate.of(2018, 6, 25), LocalDate.of(2018, 6, 25)))
        booksList.add(BorrowedBook("HarryPotter2", "J.K.Rowling", R.mipmap.ic_launcher, 5.0.toFloat(), true, "comment1", "Dasha", LocalDate.of(2018, 6, 25), LocalDate.of(2018, 6, 25)))
        booksList.add(BorrowedBook("HarryPotter3", "J.K.Rowling", R.mipmap.ic_launcher, 5.0.toFloat(), true, "comment1", "Dasha", LocalDate.of(2018, 6, 25), LocalDate.of(2018, 6, 25)))
        booksList.add(BorrowedBook("HarryPotter4", "J.K.Rowling", R.mipmap.ic_launcher, 5.0.toFloat(), true, "comment1", "Dasha", LocalDate.of(2018, 6, 25), LocalDate.of(2018, 6, 25)))
        booksList.add(BorrowedBook("HarryPotter5", "J.K.Rowling", R.mipmap.ic_launcher, 5.0.toFloat(), true, "comment1", "Dasha", LocalDate.of(2018, 6, 25), LocalDate.of(2018, 6, 25)))
        booksList.add(BorrowedBook("HarryPotter6", "J.K.Rowling", R.mipmap.ic_launcher, 5.0.toFloat(), true, "comment1", "Dasha", LocalDate.of(2018, 6, 25), LocalDate.of(2018, 6, 25)))
        booksList.add(BorrowedBook("HarryPotter7", "J.K.Rowling", R.mipmap.ic_launcher, 5.0.toFloat(), true, "comment1", "Dasha", LocalDate.of(2018, 6, 25), LocalDate.of(2018, 6, 25)))
        borrowedBooksList.value = booksList
    }
}