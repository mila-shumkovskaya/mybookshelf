package com.study.mybookshelf.utils

import android.R.attr.bitmap
import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.graphics.BitmapFactory
import android.os.Build
import androidx.annotation.RequiresApi
import java.io.ByteArrayOutputStream
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
fun LocalDate.getString(): String{
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    return this.format(formatter)
}

fun Bitmap.toByteArray(): ByteArray {
    val buffer =
        ByteArrayOutputStream(this.width * this.height)
    this.compress(CompressFormat.PNG, 100, buffer)
    return buffer.toByteArray()
}

fun ByteArray.toBitmap(): Bitmap? {
   return BitmapFactory.decodeByteArray(this, 0, this.size).resize()

}

fun Bitmap.resize(): Bitmap? {
    val width = 150
    val height = 200
    return Bitmap.createScaledBitmap(this, width, height, true);
}

