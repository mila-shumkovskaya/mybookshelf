package com.study.mybookshelf.ui.BookRecyclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.study.mybookshelf.R
import com.study.mybookshelf.model.Book
import com.study.mybookshelf.model.BorrowedBook
import com.study.mybookshelf.model.LendedBook
import com.study.mybookshelf.model.getString
import kotlinx.android.synthetic.main.recycler_view_borrowed_book_item.view.*


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

        override fun bind(book: Book) {
            tbTitle.text = book.title
            tbAuthor.text = book.author
            ivCover.setImageResource(book.photo)
        }
    }

    inner class BorrowedBookViewHolder(itemView: View) : BookViewHolder<BorrowedBook>(itemView) {
        private val bookView = itemView
        private val tbTitle: TextView = bookView.findViewById(R.id.text_book_title)
        private val tbAuthor: TextView = bookView.findViewById(R.id.text_book_author)
        private val ivCover: ImageView = itemView.findViewById(R.id.image_cover)
        private val tbRecipient: TextView = bookView.findViewById(R.id.text_book_recipient)
        private val tbReturnDate: TextView = bookView.findViewById(R.id.text_book_return_date)
        override fun bind(book: BorrowedBook) {
            tbTitle.text = book.title
            tbAuthor.text = book.author
            ivCover.setImageResource(book.photo)
            tbRecipient.text = book.recipient
            tbReturnDate.text = book.returnDate.getString()
        }
    }

    inner class LendedBookViewHolder(itemView: View) : BookViewHolder<LendedBook>(itemView) {
        private val bookView = itemView
        private val tbTitle: TextView = bookView.findViewById(R.id.text_book_title)
        private val tbAuthor: TextView = bookView.findViewById(R.id.text_book_author)
        private val ivCover: ImageView = itemView.findViewById(R.id.image_cover)
        private val tbOwner: TextView = bookView.findViewById(R.id.text_book_owner)
        private val tbReturnDate: TextView = bookView.findViewById(R.id.text_book_return_date)
        override fun bind(book: LendedBook) {
            tbTitle.text = book.title
            tbAuthor.text = book.author
            ivCover.setImageResource(book.photo)
            tbOwner.text = book.owner
            tbReturnDate.text = book.returnDate.getString()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder<*> {
        return when (viewType) {
            TYPE_BORROWED_BOOK -> {
                val view = LayoutInflater.from(context).inflate(R.layout.recycler_view_borrowed_book_item, parent, false)
                BorrowedBookViewHolder(view)
            }
            TYPE_LENDED_BOOK -> {
                val view = LayoutInflater.from(context).inflate(R.layout.recycler_view_lended_book_item, parent, false)
                LendedBookViewHolder(view)
            }
            TYPE_LIBRARY_BOOK -> {
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
            is BorrowedBook -> TYPE_BORROWED_BOOK
            is LendedBook -> TYPE_LENDED_BOOK
            else -> TYPE_LIBRARY_BOOK
        }
    }

    companion object {
        private const val TYPE_LIBRARY_BOOK = 0
        private const val TYPE_BORROWED_BOOK = 1
        private const val TYPE_LENDED_BOOK = 2
    }

    fun refreshBooks(items: List<Book>) {
        this.booksList = items
        notifyDataSetChanged()
    }
}
