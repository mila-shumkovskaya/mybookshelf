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
import com.study.mybookshelf.ui.home.HomeViewModel


class BorrowedBooksFragment : Fragment() {
    private lateinit var borrowedBooksViewModel: BorrowedBooksViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        borrowedBooksViewModel =
                ViewModelProviders.of(this).get(BorrowedBooksViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_borrowed_books, container, false)
        val textView: TextView = root.findViewById(R.id.textView3)
        borrowedBooksViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}