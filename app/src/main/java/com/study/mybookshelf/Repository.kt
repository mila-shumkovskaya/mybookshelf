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

    init {
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