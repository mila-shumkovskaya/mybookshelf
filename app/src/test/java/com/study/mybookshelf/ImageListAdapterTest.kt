package com.study.mybookshelf

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.View
import com.study.mybookshelf.google_books_api.ImageListAdapter
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import java.util.Arrays.asList

class ImageListAdapterTest {

    private val items = arrayListOf<Bitmap>()
    private val imageListAdapter =  ImageListAdapter(items, onClickListener = {})
    //private val mockView = mock<ImageListAdapter.ViewHolder>(ImageListAdapter.ViewHolder::class.java)
    //private val viewHolder = imageListAdapter.ViewHolder(mockView)

    @Before
    fun setUp() {
        //mockView.vie
    }

    @Test
    fun testGetItemCount() {
        val bitmap1 = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
        val bitmap2 = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
        items.add(bitmap1)
        items.add(bitmap2)
        assertEquals(imageListAdapter.itemCount, 2)
    }
}