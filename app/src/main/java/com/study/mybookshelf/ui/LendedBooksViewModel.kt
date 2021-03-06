package com.study.mybookshelf.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.study.mybookshelf.R
import com.study.mybookshelf.Repository
import com.study.mybookshelf.model.LendedBook
import com.study.mybookshelf.utils.getString
import java.time.LocalDate

class LendedBooksViewModel : ViewModel() {
    val lendedBooksList: MutableLiveData<List<LendedBook>> = MutableLiveData()
    private val repository: Repository<LendedBook> = Repository(lendedBooksList, LendedBook::class.java)

    init {

        val lended1 = LendedBook("lended_1", "author_1", R.mipmap.ic_launcher, 5.0.toFloat(), true, "interesting book",
            "Petya", LocalDate.of(2020, 12, 20).getString(), LocalDate.of(2021, 12, 20).getString())
        val lended2 = LendedBook("lended_2", "author_2", R.mipmap.ic_launcher, 3.0.toFloat(), false, "hi!",
            "Kate&Leo", LocalDate.of(2020, 10, 20).getString(), LocalDate.of(2021, 11, 20).getString())
        val lended3 = LendedBook("lended_3", "author_3", R.mipmap.ic_launcher, 4.7.toFloat(), true, "lendeeed",
            "Smb", LocalDate.of(2019, 12, 10).getString(), LocalDate.of(2020, 12, 20).getString())
        val lended4 = (LendedBook("HarryPotter1", "J.K.Rowling", R.mipmap.ic_launcher, 5.0.toFloat(), true, "comment1", "Dasha", LocalDate.of(2018, 6, 25).getString(), LocalDate.of(2018, 6, 25).getString()))
        val lended5 = (LendedBook("HarryPotter2", "J.K.Rowling", R.mipmap.ic_launcher, 5.0.toFloat(), true, "comment1", "Dasha", LocalDate.of(2018, 6, 25).getString(), LocalDate.of(2018, 6, 25).getString()))
        val lended6 = (LendedBook("HarryPotter3", "J.K.Rowling", R.mipmap.ic_launcher, 5.0.toFloat(), true, "comment1", "Dasha", LocalDate.of(2018, 6, 25).getString(), LocalDate.of(2018, 6, 25).getString()))
        val lended7 = (LendedBook("HarryPotter4", "J.K.Rowling", R.mipmap.ic_launcher, 5.0.toFloat(), true, "comment1", "Dasha", LocalDate.of(2018, 6, 25).getString(), LocalDate.of(2018, 6, 25).getString()))
        val lended8 = (LendedBook("HarryPotter5", "J.K.Rowling", R.mipmap.ic_launcher, 5.0.toFloat(), true, "comment1", "Dasha", LocalDate.of(2018, 6, 25).getString(), LocalDate.of(2018, 6, 25).getString()))
        val lended9 = (LendedBook("HarryPotter2", "edited", R.mipmap.ic_launcher, 5.0.toFloat(), true, "comment1", "Dasha", LocalDate.of(2018, 6, 25).getString(), LocalDate.of(2018, 6, 25).getString()))
        val lended10 = (LendedBook("HarryPotter7", "J.K.Rowling", R.mipmap.ic_launcher, 5.0.toFloat(), true, "comment1", "Dasha", LocalDate.of(2018, 6, 25).getString(), LocalDate.of(2018, 6, 25).getString()))
        createOrUpdateBook(lended1)
        createOrUpdateBook(lended2)
        createOrUpdateBook(lended3)
        createOrUpdateBook(lended4)
        createOrUpdateBook(lended5)
        createOrUpdateBook(lended6)
        createOrUpdateBook(lended7)
        createOrUpdateBook(lended8)
        createOrUpdateBook(lended9)
        createOrUpdateBook(lended10)
        deleteBook(lended4)
    }

    fun createOrUpdateBook(book: LendedBook) {
        repository.insertOrUpdateBook(book)
    }

    fun deleteBook(book: LendedBook) {
        repository.deleteBook(book.title)
    }

    override fun onCleared() {
        repository.close()
    }

}