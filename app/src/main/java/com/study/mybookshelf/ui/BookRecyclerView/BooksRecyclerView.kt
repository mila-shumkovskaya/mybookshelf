package com.study.mybookshelf.ui.BookRecyclerView

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.study.mybookshelf.R


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
        adapter = BooksListAdapter(context)
        recyclerView.adapter = adapter
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