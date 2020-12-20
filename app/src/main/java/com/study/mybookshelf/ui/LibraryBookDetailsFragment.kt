package com.study.mybookshelf.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.study.mybookshelf.R
import com.study.mybookshelf.model.Book
import com.study.mybookshelf.ui.BookRecyclerView.BooksRecyclerView
import kotlinx.android.synthetic.main.fragment_library_book_details.*

class LibraryBookDetailsFragment: Fragment() {

   // lateinit var libraryViewModel:LibraryViewModel
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
     //   libraryViewModel = ViewModelProviders.of(this).get(LibraryViewModel::class.java)
       val root = inflater.inflate(R.layout.fragment_library_book_details, container, false)
       val book:Book = requireActivity().intent.getSerializableExtra("book") as Book
       val title: EditText = root.findViewById(R.id.et_title)
       val author: EditText = root.findViewById(R.id.et_author)
       title.hint=book.title
       author.hint=book.author
       // val rvBooks: BooksRecyclerView =  root.findViewById(R.id.recycler_view_books)
        //libraryViewModel.libraryBooksList.observe(viewLifecycleOwner, Observer {
        //    rvBooks.adapter.refreshBooks(it)
       // })

        return root
    }
}