package com.study.mybookshelf.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.study.mybookshelf.R
import com.study.mybookshelf.model.LendedBook
import java.util.*

class LendedBookDetailsFragment: Fragment() {

    // lateinit var libraryViewModel:LibraryViewModel
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        //   libraryViewModel = ViewModelProviders.of(this).get(LibraryViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_lended_book_details, container, false)
        val book:LendedBook = requireActivity().intent.getSerializableExtra("book") as LendedBook

        val ivCover: ImageView = root.findViewById(R.id.iv_book_cover)
        val etTitle: EditText = root.findViewById(R.id.et_title)
        val etAuthor: EditText = root.findViewById(R.id.et_author)
        val rbRating: RatingBar = root.findViewById(R.id.rating_bar)
        val switchIsEl: Switch = root.findViewById(R.id.switch_is_el)
        val etComment: EditText = root.findViewById(R.id.et_comment)
        val etRecipient: EditText = root.findViewById(R.id.et_recipient)
        val dpTransferDate: DatePicker = root.findViewById(R.id.transfer_date_picker)
        val dpReturnDate: DatePicker = root.findViewById(R.id.return_date_picker)

        ivCover.setImageResource(book.photo)
        etTitle.hint = book.title
        etAuthor.hint = book.author
        rbRating.rating = book.rating
        switchIsEl.isChecked = book.isDigital
        etComment.hint = book.comments
        etRecipient.hint = book.recipient

        // val rvBooks: BooksRecyclerView =  root.findViewById(R.id.recycler_view_books)
        //libraryViewModel.libraryBooksList.observe(viewLifecycleOwner, Observer {
        //    rvBooks.adapter.refreshBooks(it)
        // })

        return root
    }
}