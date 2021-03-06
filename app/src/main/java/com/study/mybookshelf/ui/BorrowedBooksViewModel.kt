package com.study.mybookshelf.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.study.mybookshelf.R
import com.study.mybookshelf.Repository
import com.study.mybookshelf.model.BorrowedBook
import com.study.mybookshelf.utils.getString
import java.time.LocalDate

class BorrowedBooksViewModel : ViewModel() {

    val borrowedBooksList: MutableLiveData<List<BorrowedBook>> = MutableLiveData()
    private val repository: Repository<BorrowedBook> = Repository(borrowedBooksList, BorrowedBook::class.java)

    init {

        val borrowed1 = BorrowedBook("borrowed_1", "author_1", R.mipmap.ic_launcher, 5.0.toFloat(), true, "hi there i'm using whatsApp",
            "Ted", LocalDate.of(2019, 12, 10).getString(), LocalDate.of(2020, 12, 20).getString())
        val borrowed2 = BorrowedBook("borrowed_2", "author_2", R.mipmap.ic_launcher, 3.0.toFloat(), false, "boRRRRRowed BOOOOOk",
            "Mya", LocalDate.of(2018, 12, 10).getString(), LocalDate.of(2022, 12, 20).getString())
        val borrowed3 = (BorrowedBook("HarryPotter1", "J.K.Rowling", R.mipmap.ic_launcher, 5.0.toFloat(), true, "comment1", "Dasha", LocalDate.of(2018, 6, 25).getString(), LocalDate.of(2018, 6, 25).getString()))
        val borrowed4 = (BorrowedBook("HarryPotter2", "J.K.Rowling", R.mipmap.ic_launcher, 5.0.toFloat(), true, "comment1", "Dasha", LocalDate.of(2018, 6, 25).getString(), LocalDate.of(2018, 6, 25).getString()))
        val borrowed5 = (BorrowedBook("HarryPotter3", "J.K.Rowling", R.mipmap.ic_launcher, 5.0.toFloat(), true, "comment1", "Dasha", LocalDate.of(2018, 6, 25).getString(), LocalDate.of(2018, 6, 25).getString()))
        val borrowed6 = (BorrowedBook("HarryPotter4", "J.K.Rowling", R.mipmap.ic_launcher, 5.0.toFloat(), true, "comment1", "Dasha", LocalDate.of(2018, 6, 25).getString(), LocalDate.of(2018, 6, 25).getString()))
        val borrowed7 = (BorrowedBook("HarryPotter5", "J.K.Rowling", R.mipmap.ic_launcher, 5.0.toFloat(), true, "comment1", "Dasha", LocalDate.of(2018, 6, 25).getString(), LocalDate.of(2018, 6, 25).getString()))
        val borrowed8 = (BorrowedBook("HarryPotter6", "J.K.Rowling", R.mipmap.ic_launcher, 5.0.toFloat(), true, "comment1", "Dasha", LocalDate.of(2018, 6, 25).getString(), LocalDate.of(2018, 6, 25).getString()))
        val borrowed9 = (BorrowedBook("HarryPotter2", "edited", R.mipmap.ic_launcher, 5.0.toFloat(), true, "comment1", "Dasha", LocalDate.of(2018, 6, 25).getString(), LocalDate.of(2018, 6, 25).getString()))
        createOrUpdateBook(borrowed1)
        createOrUpdateBook(borrowed2)
        createOrUpdateBook(borrowed3)
        createOrUpdateBook(borrowed4)
        createOrUpdateBook(borrowed5)
        createOrUpdateBook(borrowed6)
        createOrUpdateBook(borrowed7)
        createOrUpdateBook(borrowed8)
        createOrUpdateBook(borrowed9)
        deleteBook(borrowed3)
    }

    fun createOrUpdateBook(book: BorrowedBook) {
        repository.insertOrUpdateBook(book)
    }

    fun deleteBook(book: BorrowedBook) {
        repository.deleteBook(book.title)
    }

    override fun onCleared() {
        repository.close()
    }

}