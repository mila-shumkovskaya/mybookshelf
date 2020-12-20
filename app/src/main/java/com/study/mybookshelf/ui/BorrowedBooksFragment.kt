package com.study.mybookshelf.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.study.mybookshelf.R
import com.study.mybookshelf.ui.book_recycler_view.BooksRecyclerView

class BorrowedBooksFragment : Fragment() {

    private lateinit var borrowedBooksViewModel: BorrowedBooksViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        borrowedBooksViewModel = ViewModelProviders.of(this).get(BorrowedBooksViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_borrowed_books, container, false)

        val rvBooks: BooksRecyclerView =  root.findViewById(R.id.recycler_view_books)
        borrowedBooksViewModel.borrowedBooksList.observe(viewLifecycleOwner, Observer {
            rvBooks.adapter.refreshBooks(it)
        })

        return root
    }
}