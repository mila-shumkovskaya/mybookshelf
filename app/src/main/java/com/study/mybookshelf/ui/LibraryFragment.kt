package com.study.mybookshelf.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.study.mybookshelf.DetailsActivity
import com.study.mybookshelf.R
import com.study.mybookshelf.model.LibraryBook
import com.study.mybookshelf.ui.book_recycler_view.BooksRecyclerView

class LibraryFragment: Fragment() {

    lateinit var libraryViewModel:LibraryViewModel
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        libraryViewModel = ViewModelProviders.of(this).get(LibraryViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_library, container, false)

        val rvBooks: BooksRecyclerView =  root.findViewById(R.id.recycler_view_books)
        libraryViewModel.libraryBooksList.observe(viewLifecycleOwner, Observer {
            rvBooks.adapter.refreshBooks(it)
        })

        val fab: FloatingActionButton = root.findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            val intent = Intent(context, DetailsActivity::class.java)
            val book = LibraryBook(getString(R.string.hint_title), getString(R.string.hint_author), R.mipmap.book_cover, 5.0.toFloat(), true, getString(R.string.hint_comment))
            //val bundle = bundleOf( "book" to book)
           intent.putExtra("book", book)
            intent.putExtra("add", true)
            startActivity(intent)
        }
        return root
    }
}