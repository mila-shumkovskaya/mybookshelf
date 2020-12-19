package com.study.mybookshelf.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.study.mybookshelf.R
import com.study.mybookshelf.model.LendedBook
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

@RequiresApi(Build.VERSION_CODES.O)
class LendedBooksViewModel : ViewModel() {

    var lendedBooksList : MutableLiveData<List<LendedBook>> = MutableLiveData()

    init {
        val booksList = ArrayList<LendedBook>()
        booksList.add(LendedBook("HarryPotter1", "J.K.Rowling", R.mipmap.ic_launcher, 5.0.toFloat(), true, "comment1", "Dasha", LocalDate.of(2018, 6, 25), LocalDate.of(2018, 6, 25)))
        booksList.add(LendedBook("HarryPotter2", "J.K.Rowling", R.mipmap.ic_launcher, 5.0.toFloat(), true, "comment1", "Dasha", LocalDate.of(2018, 6, 25), LocalDate.of(2018, 6, 25)))
        booksList.add(LendedBook("HarryPotter3", "J.K.Rowling", R.mipmap.ic_launcher, 5.0.toFloat(), true, "comment1", "Dasha", LocalDate.of(2018, 6, 25), LocalDate.of(2018, 6, 25)))
        booksList.add(LendedBook("HarryPotter4", "J.K.Rowling", R.mipmap.ic_launcher, 5.0.toFloat(), true, "comment1", "Dasha", LocalDate.of(2018, 6, 25), LocalDate.of(2018, 6, 25)))
        booksList.add(LendedBook("HarryPotter5", "J.K.Rowling", R.mipmap.ic_launcher, 5.0.toFloat(), true, "comment1", "Dasha", LocalDate.of(2018, 6, 25), LocalDate.of(2018, 6, 25)))
        booksList.add(LendedBook("HarryPotter6", "J.K.Rowling", R.mipmap.ic_launcher, 5.0.toFloat(), true, "comment1", "Dasha", LocalDate.of(2018, 6, 25), LocalDate.of(2018, 6, 25)))
        booksList.add(LendedBook("HarryPotter7", "J.K.Rowling", R.mipmap.ic_launcher, 5.0.toFloat(), true, "comment1", "Dasha", LocalDate.of(2018, 6, 25), LocalDate.of(2018, 6, 25)))
        lendedBooksList.value = booksList
    }
}