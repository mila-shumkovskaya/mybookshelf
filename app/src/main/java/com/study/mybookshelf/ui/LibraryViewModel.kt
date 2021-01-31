package com.study.mybookshelf.ui

import android.content.res.Resources
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.study.mybookshelf.R
import com.study.mybookshelf.Repository
import com.study.mybookshelf.model.LibraryBook
import com.study.mybookshelf.utils.toByteArray


class LibraryViewModel : ViewModel() {
    val libraryBooksList: MutableLiveData<List<LibraryBook>> = MutableLiveData()
    private val repository: Repository<LibraryBook> = Repository(libraryBooksList, LibraryBook::class.java)


    init {
        //val byteArray = Resources.getSystem().getDrawable(R.drawable.book_cover).toBitmap().toByteArray()
        val book1 = LibraryBook("title_1", "author_1", ByteArray(0), 5.0.toFloat(), true, "useful comments")
        val book2 = LibraryBook("title_2", "author_2", ByteArray(0), 3.0.toFloat(), false, "unuseful comments")
        val book3 = LibraryBook("title_3", "author_3", ByteArray(0), 4.7.toFloat(), false, "best comment ever")
        val book4 = (LibraryBook("HarryPotter1", "J.K.Rowling", ByteArray(0), 5.0.toFloat(), true, "comment1"))
        val book5 = (LibraryBook("HarryPotter2", "J.K.Rowling", ByteArray(0), 3.9.toFloat(), true, "comment1"))
        val book6 = (LibraryBook("HarryPotter3", "J.K.Rowling", ByteArray(0), 5.0.toFloat(), true, "comment1"))
        val book7 = (LibraryBook("HarryPotter4", "J.K.Rowling", ByteArray(0), 4.7.toFloat(), true, "comment1"))
        val book8 = (LibraryBook("HarryPotter5", "J.K.Rowling", ByteArray(0), 5.0.toFloat(), true, "comment1"))
        val book9 = (LibraryBook("HarryPotter6", "J.K.Rowling", ByteArray(0), 2.6.toFloat(), true, "comment1"))
        val book10 = (LibraryBook("HarryPotter2", "edited", ByteArray(0), 5.0.toFloat(), true, "comment1"))
        createOrUpdateBook(book1)
        createOrUpdateBook(book2)
        createOrUpdateBook(book3)
        createOrUpdateBook(book4)
        createOrUpdateBook(book5)
        createOrUpdateBook(book6)
        createOrUpdateBook(book7)
        createOrUpdateBook(book8)
        createOrUpdateBook(book9)
        createOrUpdateBook(book10)
        deleteBook(book4)
    }

    private fun createOrUpdateBook(book: LibraryBook) {
        repository.insertOrUpdateBook(book)
    }

    private fun deleteBook(book: LibraryBook) {
        repository.deleteBook(book.title)
    }

    override fun onCleared() {
        repository.close()
    }

}