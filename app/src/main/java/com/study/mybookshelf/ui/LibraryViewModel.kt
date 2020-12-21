package com.study.mybookshelf.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.study.mybookshelf.R
import com.study.mybookshelf.Repository
import com.study.mybookshelf.model.LibraryBook


class LibraryViewModel : ViewModel() {
    val libraryBooksList: MutableLiveData<List<LibraryBook>> = MutableLiveData()
    private val repository: Repository<LibraryBook> = Repository(libraryBooksList, LibraryBook::class.java)


    init {

        val book1 = LibraryBook("title_1", "author_1", R.mipmap.ic_launcher, 5.0.toFloat(), true, "useful comments")
        val book2 = LibraryBook("title_2", "author_2", R.mipmap.ic_launcher, 3.0.toFloat(), false, "unuseful comments")
        val book3 = LibraryBook("title_3", "author_3", R.mipmap.ic_launcher, 4.7.toFloat(), false, "best comment ever")
        val book4 = (LibraryBook("HarryPotter1", "J.K.Rowling", R.mipmap.ic_launcher, 5.0.toFloat(), true, "comment1"))
        val book5 = (LibraryBook("HarryPotter2", "J.K.Rowling", R.mipmap.ic_launcher, 5.0.toFloat(), true, "comment1"))
        val book6 = (LibraryBook("HarryPotter3", "J.K.Rowling", R.mipmap.ic_launcher, 5.0.toFloat(), true, "comment1"))
        val book7 = (LibraryBook("HarryPotter4", "J.K.Rowling", R.mipmap.ic_launcher, 5.0.toFloat(), true, "comment1"))
        val book8 = (LibraryBook("HarryPotter5", "J.K.Rowling", R.mipmap.ic_launcher, 5.0.toFloat(), true, "comment1"))
        val book9 = (LibraryBook("HarryPotter6", "J.K.Rowling", R.mipmap.ic_launcher, 5.0.toFloat(), true, "comment1"))
        val book10 = (LibraryBook("HarryPotter2", "edited", R.mipmap.ic_launcher, 5.0.toFloat(), true, "comment1"))
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

    fun createOrUpdateBook(book: LibraryBook) {
        repository.insertOrUpdateBook(book)
    }

    fun deleteBook(book: LibraryBook) {
        repository.deleteBook(book.title)
    }

    override fun onCleared() {
        repository.close()
    }

}