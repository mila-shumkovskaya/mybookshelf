package com.study.mybookshelf.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.Switch
import androidx.fragment.app.Fragment
import com.study.mybookshelf.R
import com.study.mybookshelf.model.Book

class LibraryBookDetailsFragment: Fragment() {

   // lateinit var libraryViewModel:LibraryViewModel
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
     //   libraryViewModel = ViewModelProviders.of(this).get(LibraryViewModel::class.java)
       val root = inflater.inflate(R.layout.fragment_library_book_details, container, false)
       val book: Book = requireActivity().intent.getSerializableExtra("book") as Book

       val ivCover: ImageView = root.findViewById(R.id.iv_book_cover)
       val etTitle: EditText = root.findViewById(R.id.et_title)
       val etAuthor: EditText = root.findViewById(R.id.et_author)
       val rbRating: RatingBar = root.findViewById(R.id.rating_bar)
       val switchIsEl: Switch = root.findViewById(R.id.switch_is_el)
       val etComment: EditText = root.findViewById(R.id.et_comment)

       ivCover.setImageResource(book.photo)
       etTitle.hint = getString(R.string.hint_title)
       etAuthor.hint = getString(R.string.hint_author)
       rbRating.rating = book.rating
       switchIsEl.isChecked = false
       etComment.hint = getString(R.string.hint_comment)

       // val rvBooks: BooksRecyclerView =  root.findViewById(R.id.recycler_view_books)
       //libraryViewModel.libraryBooksList.observe(viewLifecycleOwner, Observer {
       //    rvBooks.adapter.refreshBooks(it)
       // })

        return root
    }
}