package com.study.mybookshelf

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.study.mybookshelf.ui.LibraryBookDetailsFragment


class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager
                .beginTransaction()


        // добавляем фрагмент
        val myFragment = LibraryBookDetailsFragment()
        fragmentTransaction.add(R.id.container, myFragment)
        fragmentTransaction.commit()
    }
}