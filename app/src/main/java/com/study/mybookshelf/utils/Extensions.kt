package com.study.mybookshelf.utils

import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.graphics.BitmapFactory
import android.os.Build
import android.text.Editable
import androidx.annotation.RequiresApi
import java.io.ByteArrayOutputStream
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

const val DEFAULT_IMAGE_WIDTH = 225
const val DEFAULT_IMAGE_HEIGHT = 300
const val SHORT_STRING_MAX_LENGTH = 150
const val LONG_STRING_MAX_LENGTH = 800

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
    val bitmap = BitmapFactory.decodeByteArray(this, 0, this.size)
    return when {
        bitmap != null -> {
            bitmap.resize()
        }
        else -> null
    }
}

fun Bitmap.resize(): Bitmap? {
    val width = DEFAULT_IMAGE_WIDTH
    val height = DEFAULT_IMAGE_HEIGHT
    return Bitmap.createScaledBitmap(this, width, height, true)
}

// for testing detailsFragments - need to mock EditText.getText(), which returns Editable
fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)

// for EditText fields validation
fun String.isValidShortNotEmpty(): Boolean
        = this.isNotEmpty() && (this.length <= SHORT_STRING_MAX_LENGTH)

fun String.isValidShort(): Boolean = this.length <= SHORT_STRING_MAX_LENGTH

fun String.isValidLong(): Boolean = this.length <= LONG_STRING_MAX_LENGTH

// for DatePicker fields validation
fun Calendar.notGreaterThanDate(cMustBeGreaterDate: Calendar): Boolean {
    return this.compareTo(cMustBeGreaterDate) <= 0
}
