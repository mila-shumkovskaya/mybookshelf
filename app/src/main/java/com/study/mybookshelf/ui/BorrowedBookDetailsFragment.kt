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
import com.study.mybookshelf.model.LibraryBook
import java.text.SimpleDateFormat
import java.util.*

class BorrowedBookDetailsFragment: Fragment() {

    lateinit var ivCover: ImageView
    lateinit var etTitle: EditText
    lateinit var etAuthor: EditText
    lateinit var rbRating: RatingBar
    lateinit var switchIsEl: Switch
    lateinit var etComment: EditText
    lateinit var etOwner: EditText
    lateinit var dpReceiveDate: DatePicker
    lateinit var dpReturnDate: DatePicker

    var receiveDate = Calendar.getInstance()
    var returnDate = Calendar.getInstance()
    var today = Calendar.getInstance()

    lateinit var book: BorrowedBook

    // lateinit var libraryViewModel:LibraryViewModel
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        //   libraryViewModel = ViewModelProviders.of(this).get(LibraryViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_borrowed_book_details, container, false)
        val book: BorrowedBook = requireActivity().intent.getSerializableExtra("book") as BorrowedBook
        book = requireActivity().intent.getSerializableExtra("book") as BorrowedBook

        ivCover = root.findViewById(R.id.iv_book_cover)
        etTitle = root.findViewById(R.id.et_title)
        etAuthor = root.findViewById(R.id.et_author)
        rbRating = root.findViewById(R.id.rating_bar)
        switchIsEl = root.findViewById(R.id.switch_is_el)
        etComment = root.findViewById(R.id.et_comment)
        etOwner = root.findViewById(R.id.et_owner)
        dpReceiveDate = root.findViewById(R.id.receive_date_picker)
        dpReturnDate = root.findViewById(R.id.return_date_picker)

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

        // create an OnDateChangedListeners
        val receiveDateChangedListener = DatePicker.OnDateChangedListener {
                _: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                receiveDate.set(Calendar.YEAR, year)
                receiveDate.set(Calendar.MONTH, monthOfYear)
                receiveDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            }

        val returnDateChangedListener = DatePicker.OnDateChangedListener {
                _: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                returnDate.set(Calendar.YEAR, year)
                returnDate.set(Calendar.MONTH, monthOfYear)
                returnDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            }

        dpReceiveDate.init(today.get(Calendar.YEAR),
                           today.get(Calendar.MONTH),
                           today.get(Calendar.DAY_OF_MONTH),
                           receiveDateChangedListener)

        dpReturnDate.init(today.get(Calendar.YEAR),
                           today.get(Calendar.MONTH),
                           today.get(Calendar.DAY_OF_MONTH),
                           returnDateChangedListener)

        return root
    }

    fun getInfoFromFields(): BorrowedBook {
        val modifiedBook = BorrowedBook()

        modifiedBook.title = etTitle.toString()
        modifiedBook.author = etAuthor.toString()
        //book.photo = ivCover.getDrawable() -- need conversion to byte[]
        modifiedBook.photo = this.book.photo
        modifiedBook.rating = rbRating.rating
        modifiedBook.isDigital = switchIsEl.isChecked
        modifiedBook.comments = etComment.toString()

        modifiedBook.owner = etOwner.toString()

        val dateFormat = "yyyy-mm-dd"
        val sdf = SimpleDateFormat(dateFormat, Locale.US)
        modifiedBook.receiveDate = sdf.format(receiveDate.time)

        modifiedBook.returnDate = sdf.format(returnDate.time)

        return modifiedBook
    }
}