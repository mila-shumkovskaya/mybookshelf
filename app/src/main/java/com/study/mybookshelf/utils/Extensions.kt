package com.study.mybookshelf.utils

import android.R.attr.bitmap
import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.graphics.BitmapFactory
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.annotation.RequiresApi
import java.io.ByteArrayOutputStream
import java.time.LocalDate
import java.time.format.DateTimeFormatter

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

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object: TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            afterTextChanged.invoke(s.toString())
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }
    })
}

fun EditText.validate(message: String, validator: (String) -> Boolean) {
    this.afterTextChanged {
        this.error = if (validator(it)) null else message
    }
    this.error = if (validator(this.text.toString())) null else message
}
