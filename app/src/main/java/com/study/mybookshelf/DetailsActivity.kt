package com.study.mybookshelf

import android.app.ActionBar
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.study.mybookshelf.model.Book
import com.study.mybookshelf.model.BorrowedBook
import com.study.mybookshelf.model.LendedBook
import com.study.mybookshelf.model.LibraryBook
import com.study.mybookshelf.ui.BorrowedBookDetailsFragment
import com.study.mybookshelf.ui.LendedBookDetailsFragment
import com.study.mybookshelf.ui.LibraryBookDetailsFragment
import io.realm.Realm
import io.realm.kotlin.deleteFromRealm


class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager
                .beginTransaction()

        val book= intent.getSerializableExtra("book")
        val add = intent.getBooleanExtra("add", false)
        val delete: ImageButton = findViewById(R.id.bt_delete)
        val save: Button =findViewById(R.id.bt_save)
        val edit: ImageButton = findViewById(R.id.bt_edit)
        if(!add)
        {
            val params=save.layoutParams
            params.height=0
            save.layoutParams=params
        }
        else
        {
            val params1=edit.layoutParams
            params1.height=0
            edit.layoutParams=params1
            val params2=delete.layoutParams
            params2.height=0
            delete.layoutParams=params2
        }

        if(book is LendedBook)
        {
            val myFragment = LendedBookDetailsFragment()
            fragmentTransaction.add(R.id.container, myFragment)
            fragmentTransaction.commit()
        }else
        if(book is BorrowedBook)
        {
            val myFragment = BorrowedBookDetailsFragment()
            fragmentTransaction.add(R.id.container, myFragment)
            fragmentTransaction.commit()
        }
        else
        if(book is Book)
        {
            val myFragment = LibraryBookDetailsFragment()
            fragmentTransaction.add(R.id.container, myFragment)
            fragmentTransaction.commit()
        }

        delete.setOnClickListener {
            val realm: Realm = Realm.getDefaultInstance()
            book as Book
            realm.executeTransaction { realm ->
                if (book is LibraryBook){
                val delbook = realm.where(LibraryBook::class.java).equalTo("title", book.title).findFirst()
                delbook?.deleteFromRealm()
                }
                if (book is LendedBook){
                    val delbook = realm.where(LendedBook::class.java).equalTo("title", book.title).findFirst()
                    delbook?.deleteFromRealm()
                }
                if (book is BorrowedBook){
                    val delbook = realm.where(BorrowedBook::class.java).equalTo("title", book.title).findFirst()
                    delbook?.deleteFromRealm()
                }

            }
            onBackPressed()
        }


        edit.setOnClickListener {
            val params=save.layoutParams
            params.height= ActionBar.LayoutParams.WRAP_CONTENT
            save.layoutParams=params
            //make fields editable
        }

        save.setOnClickListener {
            //get data and save to realm
            onBackPressed()
        }
    }
}
