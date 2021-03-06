package com.study.mybookshelf.ui.book_recycler_view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.study.mybookshelf.R
import com.study.mybookshelf.model.Book
import com.study.mybookshelf.model.BorrowedBook
import com.study.mybookshelf.model.LendedBook
import com.study.mybookshelf.utils.BookType



class BooksListAdapter(private val context: Context): RecyclerView.Adapter<BooksListAdapter.BookViewHolder<*>>()  {

    private var booksList: List<Book> = ArrayList()

    abstract class BookViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(book: T)
    }

    inner class LibraryBookViewHolder(itemView: View) : BookViewHolder<Book>(itemView) {
        private val bookView = itemView
        private val tbTitle: TextView = bookView.findViewById(R.id.text_book_title)
        private val tbAuthor: TextView = bookView.findViewById(R.id.text_book_author)
        private val ivCover: ImageView = itemView.findViewById(R.id.image_cover)
        private val tbRating: RatingBar = itemView.findViewById(R.id.rating_bar)

        override fun bind(book: Book) {
            tbTitle.text = book.title
            tbAuthor.text = book.author
            ivCover.setImageResource(book.photo)
            tbRating.rating = book.rating
        }
    }

    inner class BorrowedBookViewHolder(itemView: View) : BookViewHolder<BorrowedBook>(itemView) {
        private val bookView = itemView
        private val tbTitle: TextView = bookView.findViewById(R.id.text_book_title)
        private val tbAuthor: TextView = bookView.findViewById(R.id.text_book_author)
        private val tbRating: RatingBar = itemView.findViewById(R.id.rating_bar)
        private val ivCover: ImageView = itemView.findViewById(R.id.image_cover)
        private val tbOwner: TextView = bookView.findViewById(R.id.text_book_owner)
        private val tbReturnDate: TextView = bookView.findViewById(R.id.text_book_return_date)
        override fun bind(book: BorrowedBook) {
            tbTitle.text = book.title
            tbAuthor.text = book.author
            ivCover.setImageResource(book.photo)
            tbRating.rating = book.rating
            tbTitle.text = book.title
            tbAuthor.text = book.author
            ivCover.setImageResource(book.photo)
            tbRating.rating = book.rating
            tbOwner.text = book.owner
            tbReturnDate.text = book.returnDate
        }
    }

    inner class LendedBookViewHolder(itemView: View) : BookViewHolder<LendedBook>(itemView) {
        private val bookView = itemView
        private val tbTitle: TextView = bookView.findViewById(R.id.text_book_title)
        private val tbAuthor: TextView = bookView.findViewById(R.id.text_book_author)
        private val ivCover: ImageView = itemView.findViewById(R.id.image_cover)
        private val tbRating: RatingBar = itemView.findViewById(R.id.rating_bar)
        private val tbRecipient: TextView = bookView.findViewById(R.id.text_book_recipient)
        private val tbReturnDate: TextView = bookView.findViewById(R.id.text_book_return_date)
        override fun bind(book: LendedBook) {
            tbTitle.text = book.title
            tbAuthor.text = book.author
            ivCover.setImageResource(book.photo)
            tbRating.rating = book.rating
            tbRecipient.text = book.recipient
            tbReturnDate.text = book.returnDate
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder<*> {
        return when (viewType) {
            BookType.BORROWED_BOOK.id -> {
                val view = LayoutInflater.from(context).inflate(R.layout.recycler_view_borrowed_book_item, parent, false)
                BorrowedBookViewHolder(view)
            }
            BookType.LENDED_BOOK.id -> {
                val view = LayoutInflater.from(context).inflate(R.layout.recycler_view_lended_book_item, parent, false)
                LendedBookViewHolder(view)
            }
            BookType.BOOK.id -> {
                val view = LayoutInflater.from(context).inflate(R.layout.recycler_view_book_item, parent, false)
                LibraryBookViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }


    override fun onBindViewHolder(viewHolder: BookViewHolder<*>, position: Int) {
        val book = booksList[position]
        when (viewHolder) {
            is BorrowedBookViewHolder -> viewHolder.bind(book as BorrowedBook)
            is LendedBookViewHolder -> viewHolder.bind(book as LendedBook)
            is LibraryBookViewHolder -> viewHolder.bind(book)
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemCount(): Int {
        return booksList.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (booksList[position]){
            is BorrowedBook -> BookType.BORROWED_BOOK.id
            is LendedBook -> BookType.LENDED_BOOK.id
            else -> BookType.BOOK.id
        }
    }

    fun refreshBooks(items: List<Book>) {
        this.booksList = items
        notifyDataSetChanged()
    }
}
