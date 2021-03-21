package com.study.mybookshelf.utils

import android.widget.*
import androidx.core.graphics.drawable.toBitmap
import com.study.mybookshelf.model.BorrowedBook
import com.study.mybookshelf.model.LendedBook
import com.study.mybookshelf.model.LibraryBook
import java.text.SimpleDateFormat
import java.util.*

fun getLibraryBookFromFields(etTitle: EditText, etAuthor: EditText, ivCover: ImageView, rbRating: RatingBar,
                            switchIsEl: Switch, etComment: EditText): LibraryBook {
    val modifiedBook = LibraryBook()

    modifiedBook.title = etTitle.text.toString()
    modifiedBook.author = etAuthor.text.toString()
    modifiedBook.photo = ivCover.drawable.toBitmap().resize()!!.toByteArray()
    modifiedBook.rating = rbRating.rating
    modifiedBook.isDigital = switchIsEl.isChecked
    modifiedBook.comments = etComment.text.toString()

    return modifiedBook
}

fun getBorrowedBookFromFields(etTitle: EditText, etAuthor: EditText, ivCover: ImageView, rbRating: RatingBar,
                      switchIsEl: Switch, etComment: EditText, etOwner: EditText,
                      receiveDate: Calendar, returnDate: Calendar): BorrowedBook {
    val modifiedBook = BorrowedBook()

    modifiedBook.title = etTitle.text.toString()
    modifiedBook.author = etAuthor.text.toString()
    modifiedBook.photo = ivCover.drawable.toBitmap().resize()!!.toByteArray()
    modifiedBook.rating = rbRating.rating
    modifiedBook.isDigital = switchIsEl.isChecked
    modifiedBook.comments = etComment.text.toString()

    modifiedBook.owner = etOwner.text.toString()

    val dateFormat = "dd.MM.yyyy"
    val sdf = SimpleDateFormat(dateFormat, Locale.getDefault())
    modifiedBook.receiveDate = sdf.format(receiveDate.time)

    modifiedBook.returnDate = sdf.format(returnDate.time)

    return modifiedBook
}

fun getLendedBookFromFields(etTitle: EditText, etAuthor: EditText, ivCover: ImageView, rbRating: RatingBar,
                            switchIsEl: Switch, etComment: EditText, etRecipient: EditText,
                            transferDate: Calendar, returnDate: Calendar): LendedBook {
    val modifiedBook = LendedBook()

    modifiedBook.title = etTitle.text.toString()
    modifiedBook.author = etAuthor.text.toString()
    modifiedBook.photo = ivCover.drawable.toBitmap().resize()!!.toByteArray()
    modifiedBook.rating = rbRating.rating
    modifiedBook.isDigital = switchIsEl.isChecked
    modifiedBook.comments = etComment.text.toString()

    modifiedBook.recipient = etRecipient.text.toString()

    val dateFormat = "dd.MM.yyyy"
    val sdf = SimpleDateFormat(dateFormat, Locale.US)
    modifiedBook.transferDate = sdf.format(transferDate.time)

    modifiedBook.returnDate = sdf.format(returnDate.time)

    return modifiedBook
}