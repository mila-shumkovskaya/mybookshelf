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
import com.study.mybookshelf.model.BorrowedBook
import com.study.mybookshelf.ui.book_recycler_view.BooksRecyclerView
import com.study.mybookshelf.utils.getString
import java.time.LocalDate

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

        val fab: FloatingActionButton = root.findViewById(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(context, DetailsActivity::class.java)
            val book  = BorrowedBook(getString(R.string.hint_title), getString(R.string.hint_author), R.mipmap.book_cover, 5.0.toFloat(), true, getString(R.string.hint_comment),
                getString(R.string.hint_owner), LocalDate.of(2019, 12, 10).getString(), LocalDate.of(2020, 12, 20).getString())
            //val bundle = bundleOf( "book" to book)
            intent.putExtra("book", book)
            intent.putExtra("add", true)
            startActivity(intent)
        }
        return root
    }
}