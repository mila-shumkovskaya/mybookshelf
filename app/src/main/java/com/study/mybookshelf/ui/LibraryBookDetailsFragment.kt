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
import com.study.mybookshelf.model.LibraryBook

class LibraryBookDetailsFragment: Fragment() {

    lateinit var ivCover: ImageView
    lateinit var etTitle: EditText
    lateinit var etAuthor: EditText
    lateinit var rbRating: RatingBar
    lateinit var switchIsEl: Switch
    lateinit var etComment: EditText

    lateinit var book: Book

   // lateinit var libraryViewModel:LibraryViewModel
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
     //   libraryViewModel = ViewModelProviders.of(this).get(LibraryViewModel::class.java)
       val root = inflater.inflate(R.layout.fragment_library_book_details, container, false)
       book = requireActivity().intent.getSerializableExtra("book") as Book

       ivCover = root.findViewById(R.id.iv_book_cover)
       etTitle = root.findViewById(R.id.et_title)
       etAuthor = root.findViewById(R.id.et_author)
       rbRating = root.findViewById(R.id.rating_bar)
       switchIsEl = root.findViewById(R.id.switch_is_el)
       etComment = root.findViewById(R.id.et_comment)

       ivCover.setImageResource(book.photo)
       etTitle.hint = book.title
       etAuthor.hint = book.author
       rbRating.rating = book.rating
       switchIsEl.isChecked = book.isDigital
       etComment.hint = book.comments

       // val rvBooks: BooksRecyclerView =  root.findViewById(R.id.recycler_view_books)
       //libraryViewModel.libraryBooksList.observe(viewLifecycleOwner, Observer {
       //    rvBooks.adapter.refreshBooks(it)
       // })

        return root
    }

    fun getInfoFromFields(): LibraryBook {
        val modifiedBook = LibraryBook()

        modifiedBook.title = etTitle.toString()
        modifiedBook.author = etAuthor.toString()
        //book.photo = ivCover.getDrawable() -- need conversion to byte[]
        modifiedBook.photo = this.book.photo
        modifiedBook.rating = rbRating.rating
        modifiedBook.isDigital = switchIsEl.isChecked
        modifiedBook.comments = etComment.toString()

        return modifiedBook
    }
}