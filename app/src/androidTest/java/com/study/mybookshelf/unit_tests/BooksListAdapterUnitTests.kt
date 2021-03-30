package com.study.mybookshelf.unit_tests

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.study.mybookshelf.model.Book
import com.study.mybookshelf.model.BorrowedBook
import com.study.mybookshelf.model.LendedBook
import com.study.mybookshelf.model.LibraryBook
import com.study.mybookshelf.ui.book_recycler_view.BooksListAdapter
import com.study.mybookshelf.utils.BookType
import com.study.mybookshelf.utils.getString
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import java.time.LocalDate

class BooksListAdapterUnitTests {

    private val context = mock(Context::class.java)
    private lateinit var bookListAdapter: BooksListAdapter
    private var recyclerViewMock = mock(RecyclerView::class.java)

    @Before
    fun setUp() {
        bookListAdapter = BooksListAdapter(context, clickListener = {})
        recyclerViewMock.adapter = bookListAdapter
    }

    @Test
    fun testGetItemCount() {
        val book1 = LibraryBook(0,"book1", "author1", ByteArray(0), 5.0.toFloat(), true, "comment1")
        val book2 = LibraryBook(1, "book2", "author2", ByteArray(0), 3.0.toFloat(), false, "comment2")
        val book3 =  LibraryBook(2, "book3", "author3", ByteArray(0), 3.0.toFloat(), false, "comment3")
        val book4 =  LibraryBook(3, "book4", "author4", ByteArray(0), 3.0.toFloat(), false, "comment4")
        bookListAdapter.refreshBooks(listOf<Book>(book1, book2, book3, book4))
        assertEquals(4, bookListAdapter.itemCount)
    }

    @Test
    fun testGetItemViewTypeOfLibraryBook() {
        val book = LibraryBook(0, "book", "author", ByteArray(0), 5.0.toFloat(), true, "comment")
        bookListAdapter.refreshBooks(listOf<Book>(book))
        assertEquals(bookListAdapter.getItemViewType(0), BookType.BOOK.id)
    }

    @Test
    fun testGetItemViewTypeOfLendedBook() {
        val lendedBook = LendedBook(0, "lended", "author", ByteArray(0), 5.0.toFloat(), true, "comment",
            "Petya", LocalDate.of(2020, 12, 20).getString(), LocalDate.of(2021, 12, 20).getString())
        bookListAdapter.refreshBooks(listOf<Book>(lendedBook))
        assertEquals(bookListAdapter.getItemViewType(0), BookType.LENDED_BOOK.id)
    }

    @Test
    fun testGetItemViewTypeOfBorrowedBook() {
        val borrowedBook = BorrowedBook(0, "borrowed", "author", ByteArray(0), 5.0.toFloat(), true, "comment",
            "Ted", LocalDate.of(2019, 12, 10).getString(), LocalDate.of(2020, 12, 20).getString())
        bookListAdapter.refreshBooks(listOf<Book>(borrowedBook))
        assertEquals(bookListAdapter.getItemViewType(0), BookType.BORROWED_BOOK.id)
    }

    @Test
    fun testRefreshBooks() {
        val book1 = LibraryBook(0, "book1", "author1", ByteArray(0), 5.0.toFloat(), true, "comment1")
        val book2 = LibraryBook(1, "book2", "author2", ByteArray(0), 3.0.toFloat(), false, "comment2")
        val book3 =  LibraryBook(2, "book3", "author3", ByteArray(0), 3.0.toFloat(), false, "comment3")
        bookListAdapter.refreshBooks(listOf<Book>(book1, book2, book3))
        //verify(bookListAdapter)?.notifyDataSetChanged()
        assertEquals(3, bookListAdapter.itemCount)
    }
}
