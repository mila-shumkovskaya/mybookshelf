package com.study.mybookshelf.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.study.mybookshelf.R
import com.study.mybookshelf.model.Book
import com.study.mybookshelf.model.BorrowedBook

class BorrowedBookDetailsFragment: Fragment() {

    // lateinit var libraryViewModel:LibraryViewModel
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        //   libraryViewModel = ViewModelProviders.of(this).get(LibraryViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_borrowed_book_details, container, false)
        val book: BorrowedBook = requireActivity().intent.getSerializableExtra("book") as BorrowedBook

        val ivCover: ImageView = root.findViewById(R.id.iv_book_cover)
        val etTitle: EditText = root.findViewById(R.id.et_title)
        val etAuthor: EditText = root.findViewById(R.id.et_author)
        val rbRating: RatingBar = root.findViewById(R.id.rating_bar)
        val switchIsEl: Switch = root.findViewById(R.id.switch_is_el)
        val etComment: EditText = root.findViewById(R.id.et_comment)
        val etOwner: EditText = root.findViewById(R.id.et_owner)
        val dpReceiveDate: DatePicker = root.findViewById(R.id.receive_date_picker)
        val dpReturnDate: DatePicker = root.findViewById(R.id.return_date_picker)

        ivCover.setImageResource(book.photo)
        etTitle.hint = book.title
        etAuthor.hint = book.author
        rbRating.rating = book.rating
        switchIsEl.isChecked = book.isDigital
        etComment.hint = book.comments
        etOwner.hint = book.owner

        // val rvBooks: BooksRecyclerView =  root.findViewById(R.id.recycler_view_books)
        //libraryViewModel.libraryBooksList.observe(viewLifecycleOwner, Observer {
        //    rvBooks.adapter.refreshBooks(it)
        // })

        return root
    }
}