package com.study.mybookshelf.google_books_api

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.RecyclerView
import com.study.mybookshelf.R
import com.study.mybookshelf.utils.resize
import com.study.mybookshelf.utils.toByteArray

class ImageListAdapter(
    private val items: ArrayList<Bitmap>,
    var onClickListener: (byteArray: ByteArray) -> Unit
) : RecyclerView.Adapter<ImageListAdapter.ViewHolder?>() {

    inner class ViewHolder(val view: View) :
        RecyclerView.ViewHolder(view) {
        val ivCover: ImageView = view.findViewById(R.id.iv_cover)
        val cvCover: CardView = view.findViewById(R.id.cv_cover)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.dialog_internet_covers_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.ivCover.setImageBitmap(items[position])
        holder.ivCover.setImageBitmap(holder.ivCover.drawable.toBitmap().resize())
        holder.cvCover.setOnClickListener {
            onClickListener(holder.ivCover.drawable.toBitmap().toByteArray())
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
