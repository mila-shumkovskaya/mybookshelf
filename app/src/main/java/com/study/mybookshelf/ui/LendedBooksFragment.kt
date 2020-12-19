package com.study.mybookshelf.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.study.mybookshelf.R
import com.study.mybookshelf.ui.BookRecyclerView.BooksRecyclerView

class LendedBooksFragment: Fragment() {

    private lateinit var lendedBooksViewModel: LendedBooksViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        lendedBooksViewModel = ViewModelProviders.of(this).get(LendedBooksViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_lended_books, container, false)

        val rvBooks: BooksRecyclerView =  root.findViewById(R.id.recycler_view_books)
        lendedBooksViewModel.lendedBooksList.observe(viewLifecycleOwner, Observer {
            rvBooks.adapter.refreshBooks(it)
        })

        return root
    }
}