package com.study.mybookshelf

import android.graphics.Bitmap
import android.text.Editable
import com.study.mybookshelf.utils.DEFAULT_IMAGE_HEIGHT
import com.study.mybookshelf.utils.DEFAULT_IMAGE_WIDTH
import com.study.mybookshelf.utils.resize
import com.study.mybookshelf.utils.toEditable
import org.junit.Test

import org.junit.Assert.*

class ExtensionsUnitTest {

    @Test
    fun testBitmapResizeToHigher() {
        val bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888)
        val newBitmap = bitmap.resize()
        assertEquals(newBitmap!!.width, DEFAULT_IMAGE_WIDTH)
        assertEquals(newBitmap.height, DEFAULT_IMAGE_HEIGHT)
        assertEquals(bitmap.width, 100)
        assertEquals(bitmap.height, 100)
    }

    @Test
    fun testBitmapResizeToLower() {
        val bitmap = Bitmap.createBitmap(600, 600, Bitmap.Config.ARGB_8888)
        val newBitmap = bitmap.resize()
        assertEquals(newBitmap!!.width, DEFAULT_IMAGE_WIDTH)
        assertEquals(newBitmap.height, DEFAULT_IMAGE_HEIGHT)
        assertEquals(bitmap.width, 600)
        assertEquals(bitmap.height, 600)
    }

    @Test
    fun testBitmapNullResize() {
        val bitmap: Bitmap? = null
        val newBitmap = bitmap?.resize()
        assertEquals(newBitmap, null)
    }

    @Test
    fun testStringToEditable() {
        val str = "string"
        val editable: Editable = Editable.Factory.getInstance().newEditable(str)
        assertEquals(str, editable.toString())
    }
}