package com.study.mybookshelf

import android.app.ActionBar
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
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
      //  val add = intent.getBooleanExtra("add", false)


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
        if(book is LibraryBook)
        {
            val myFragment = LibraryBookDetailsFragment()
            fragmentTransaction.add(R.id.container, myFragment)
            fragmentTransaction.commit()
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.i("bitmap", "onActivityResult in DetailsActivity")
        super.onActivityResult(requestCode, resultCode, data)
        val fragments: List<Fragment> =
            supportFragmentManager.fragments
        for (fragment in fragments) {
            fragment.onActivityResult(requestCode, resultCode, data)
        }
    }
}
