package com.study.mybookshelf

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.study.mybookshelf.model.BorrowedBook
import com.study.mybookshelf.model.LendedBook
import com.study.mybookshelf.model.LibraryBook
import com.study.mybookshelf.ui.fragments.BorrowedBookDetailsFragment
import com.study.mybookshelf.ui.fragments.LendedBookDetailsFragment
import com.study.mybookshelf.ui.fragments.LibraryBookDetailsFragment

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager
                .beginTransaction()

        when (intent.getSerializableExtra("book")) {
            is LendedBook -> {
                val myFragment = LendedBookDetailsFragment()
                fragmentTransaction.add(R.id.container, myFragment)
                fragmentTransaction.commit()
            }
            is BorrowedBook -> {
                val myFragment = BorrowedBookDetailsFragment()
                fragmentTransaction.add(R.id.container, myFragment)
                fragmentTransaction.commit()
            }
            is LibraryBook -> {
                val myFragment = LibraryBookDetailsFragment()
                fragmentTransaction.add(R.id.container, myFragment)
                fragmentTransaction.commit()
            }
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
