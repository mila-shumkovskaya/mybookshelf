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

        val book1 = LibraryBook(19,"title_1", "author_1", R.mipmap.book_cover, 5.0.toFloat(), true, "useful comments")
        val book2 = LibraryBook(20,"title_2", "author_2", R.mipmap.book_cover, 3.0.toFloat(), false, "unuseful comments")
        val book3 = LibraryBook(21,"title_3", "author_3", R.mipmap.book_cover, 4.7.toFloat(), false, "best comment ever")
        val book4 = (LibraryBook(22,"HarryPotter1", "J.K.Rowling", R.mipmap.book_cover, 5.0.toFloat(), true, "comment1"))
        val book5 = (LibraryBook(23,"HarryPotter2", "J.K.Rowling", R.mipmap.book_cover, 3.9.toFloat(), true, "comment1"))
        val book6 = (LibraryBook(24,"HarryPotter3", "J.K.Rowling", R.mipmap.book_cover, 5.0.toFloat(), true, "comment1"))
        val book7 = (LibraryBook(25,"HarryPotter4", "J.K.Rowling", R.mipmap.book_cover, 4.7.toFloat(), true, "comment1"))
        val book8 = (LibraryBook(26,"HarryPotter5", "J.K.Rowling", R.mipmap.book_cover, 5.0.toFloat(), true, "comment1"))
        val book9 = (LibraryBook(27,"HarryPotter6", "J.K.Rowling", R.mipmap.book_cover, 2.6.toFloat(), true, "comment1"))
        val book10 = (LibraryBook(28,"HarryPotter2", "edited", R.mipmap.book_cover, 5.0.toFloat(), true, "comment1"))
        //val byteArray = Resources.getSystem().getDrawable(R.drawable.book_cover).toBitmap().toByteArray()
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
        deleteBook(book2)
    }

    private fun createOrUpdateBook(book: LibraryBook) {
        repository.insertOrUpdateBook(book)
    }

    fun deleteBook(book: LibraryBook) {
        repository.deleteBook(book.id)
    }

    override fun onCleared() {
        repository.close()
    }

}