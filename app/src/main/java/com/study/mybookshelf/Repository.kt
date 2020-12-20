package com.study.mybookshelf

import androidx.lifecycle.MutableLiveData
import com.study.mybookshelf.model.Book
import io.realm.Realm
import io.realm.RealmChangeListener
import io.realm.RealmModel
import io.realm.RealmResults
import io.realm.kotlin.deleteFromRealm

class Repository<T : RealmModel?>(booksList: MutableLiveData<List<T>>, private var bookClass: Class<T>)
    {
    var realm: Realm = Realm.getDefaultInstance()
    private var results: RealmResults<T> = realm.where(bookClass).sort("title").findAll()

    private var realmChangeListener: RealmChangeListener<RealmResults<T>> =
        RealmChangeListener { results ->
            if (results.isLoaded && results.isValid && results.isNotEmpty()) {
                booksList.value = results as List<T>
            } else {
                booksList.value = listOf()
            }
        }

    /*private var libraryBooksResults: RealmResults<LibraryBook>? = realm.where<LibraryBook>().sort("title").findAll()
    private var borrowedBooksResults: RealmResults<BorrowedBook> = realm.where<BorrowedBook>().sort("title").findAll()
    private var lendedBooksResults: RealmResults<LendedBook>? = realm.where<LendedBook>().sort("title").findAll()*/

    /*private var realmLibraryBooksChangeListener: RealmChangeListener<RealmResults<LibraryBook?>> =
        RealmChangeListener { results ->
            if (results.isLoaded && results.isValid && results.isNotEmpty()) {
                libraryBooksList.value = results as List<LibraryBook>
            } else {
                libraryBooksList.value = listOf()
            }
        }

    private var realmBorrowedBooksChangeListener: RealmChangeListener<RealmResults<BorrowedBook?>> =
        RealmChangeListener { results ->
            if (results.isLoaded && results.isValid && results.isNotEmpty()) {
                borrowedBooksList.value = results as List<BorrowedBook>
            } else {
                borrowedBooksList.value = listOf()
            }
        }

    private var realmLendedBooksChangeListener: RealmChangeListener<RealmResults<LendedBook?>> =
        RealmChangeListener { results ->
            if (results.isLoaded && results.isValid && results.isNotEmpty()) {
                lendedBooksList.value = results as List<LendedBook>
            } else {
                lendedBooksList.value = listOf()
            }

        }*/


    init {
        /*when (bookType) {
            BookType.BOOK -> {
                results = realm.where<LibraryBook>().sort("title").findAll()
            }
            BookType.BORROWED_BOOK -> {
                results = realm.where<BorrowedBook>().sort("title").findAll()
            }
            BookType.LENDED_BOOK -> {
                results = realm.where<LendedBook>().sort("title").findAll()
            }
        }*/
        results.addChangeListener(realmChangeListener)
    }


    fun insertOrUpdateBook(book: Book) {
        realm.executeTransaction { realm ->
            realm.insertOrUpdate(book)
        }
    }


    fun deleteBook(title: String) {
        realm.executeTransaction { realm ->
            val book = realm.where(bookClass).equalTo("title", title).findFirst()
            book?.deleteFromRealm()
        }

    }

    fun close() {
        results.removeChangeListener(realmChangeListener)
        realm.close()
    }

}