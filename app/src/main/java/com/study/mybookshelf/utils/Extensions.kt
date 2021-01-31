package com.study.mybookshelf.utils

import android.R.attr.bitmap
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.annotation.RequiresApi
import java.nio.ByteBuffer
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
fun LocalDate.getString(): String{
    var formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    return this.format(formatter)
}

fun Bitmap.toByteArray(): ByteArray {
    val size = this.byteCount

    val buffer = ByteBuffer.allocate(size)
    val bytes = ByteArray(size)

    //copy the bitmap's pixels into the specified buffer
    this.copyPixelsToBuffer(buffer)
    //rewinds buffer (buffer position is set to zero and the mark is discarded)
    buffer.rewind()
    //transfer bytes from buffer into the given destination array
    buffer.get(bytes)

    return bytes
}

fun ByteArray.toBitmap(): Bitmap {
    return BitmapFactory.decodeByteArray(this, 0, this.size);
}