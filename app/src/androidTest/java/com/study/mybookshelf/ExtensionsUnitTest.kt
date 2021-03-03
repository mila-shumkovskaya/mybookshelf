package com.study.mybookshelf

import android.graphics.Bitmap
import androidx.core.graphics.drawable.toBitmap
import androidx.test.rule.ActivityTestRule
import com.study.mybookshelf.utils.*
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import java.time.LocalDate

class ExtensionsUnitTest {

    @Rule
    @JvmField
    var mainActivityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testLocalDateToString() {
        val localDate = LocalDate.of(2021, 3, 2)
        assertEquals(localDate.getString(), "02.03.2021")
    }

    @Test
    fun testByteArrayAndBitmapConversion() {
        val bitmap = mainActivityRule.activity.resources.getDrawable(R.mipmap.book_cover).toBitmap().resize()
        val byteArray = bitmap!!.toByteArray()
        val newBitmap = byteArray.toBitmap()
        assertEquals(bitmap.width, newBitmap!!.width)
        assertEquals(bitmap.height, newBitmap!!.height)
    }

    @Test
    fun testEmptyByteArrayToBitmap() {
        val emptyByteArray = ByteArray(0)
        assertEquals(emptyByteArray.toBitmap(), null)
    }

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
}