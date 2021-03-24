package com.study.mybookshelf.ui.fragments

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
import com.study.mybookshelf.model.LendedBook
import com.study.mybookshelf.ui.preferences.SharedPreferencesId
import com.study.mybookshelf.ui.book_recycler_view.BooksRecyclerView
import com.study.mybookshelf.utils.getString
import com.study.mybookshelf.view_models.LendedBooksViewModel
import java.time.LocalDate

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

        val fab: FloatingActionButton = root.findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            val intent = Intent(context, DetailsActivity::class.java)
            //read from shared
            val id = SharedPreferencesId(requireContext()).getId()+1
            val book = LendedBook(id, getString(R.string.hint_title), getString(R.string.hint_author), ByteArray(0), 5.0.toFloat(), true, getString(R.string.hint_comment),
                getString(R.string.hint_recipient), LocalDate.of(2020, 12, 20).getString(), LocalDate.of(2021, 12, 20).getString())
            //val bundle = bundleOf( "book" to book)
            intent.putExtra("book", book)
            intent.putExtra("add", true)
            startActivity(intent)
        }
        return root
    }
}