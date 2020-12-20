package com.study.mybookshelf

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.study.mybookshelf.model.Book
import com.study.mybookshelf.model.BorrowedBook
import com.study.mybookshelf.model.LendedBook
import com.study.mybookshelf.ui.BorrowedBookDetailsFragment
import com.study.mybookshelf.ui.LendedBookDetailsFragment
import com.study.mybookshelf.ui.LibraryBookDetailsFragment


class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager
                .beginTransaction()

        val book= intent.getSerializableExtra("book")

        // добавляем фрагмент

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

    }
}