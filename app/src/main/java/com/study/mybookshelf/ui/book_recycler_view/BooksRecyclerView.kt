package com.study.mybookshelf.ui.book_recycler_view

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.study.mybookshelf.DetailsActivity
import com.study.mybookshelf.MainActivity
import com.study.mybookshelf.R
import com.study.mybookshelf.model.Book
import com.study.mybookshelf.model.BorrowedBook
import com.study.mybookshelf.utils.getString
import java.time.LocalDate


class BooksRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): LinearLayout(context, attrs, defStyleAttr) {

    private var recyclerView: RecyclerView
    var adapter: BooksListAdapter

    init {
        inflate(context,
            R.layout.recycler_view_books, this)
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = GridLayoutManager(this.context, 3)
        val itemDecoration = ItemOffsetDecoration(context, R.dimen.item_offset)
        recyclerView.addItemDecoration(itemDecoration)
        adapter = BooksListAdapter(context){ book->bookClicked(book)}

        recyclerView.adapter = adapter

    }
    
    private fun bookClicked(book: Book) {
        val intent = Intent(context, DetailsActivity::class.java)
        intent.putExtra("book", book)
        Toast.makeText(context, "Its toast from book "+book.title, Toast.LENGTH_SHORT).show()
       // context.startActivity(intent)//this fucking place crush all
    }


    }

class ItemOffsetDecoration(private val mItemOffset: Int) : ItemDecoration() {

    constructor(
        context: Context,
        @DimenRes itemOffsetId: Int
    ) : this(context.resources.getDimensionPixelSize(itemOffsetId))

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.set(mItemOffset, mItemOffset, mItemOffset, mItemOffset)
    }


}