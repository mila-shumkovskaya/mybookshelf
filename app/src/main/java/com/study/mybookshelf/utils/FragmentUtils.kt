package com.study.mybookshelf.utils

import android.widget.*
import androidx.core.graphics.drawable.toBitmap
import com.study.mybookshelf.R
import com.study.mybookshelf.model.Book
import com.study.mybookshelf.model.BorrowedBook
import com.study.mybookshelf.model.LendedBook
import java.text.SimpleDateFormat
import java.util.*

fun setCoverToImageView(ivCover: ImageView, book: Book) {
    if (book.photo.isEmpty()) {
        ivCover.setImageResource(R.mipmap.book_cover_foreground)
        ivCover.setImageBitmap(ivCover.drawable.toBitmap().resize())
    } else {
        ivCover.setImageBitmap(book.photo.toBitmap())
    }
}

fun setBookInfoToFields(book: Book, hintStyle: Boolean, etTitle: EditText, etAuthor: EditText, ivCover: ImageView, rbRating: RatingBar,
                        switchIsEl: Switch, etComment: EditText) {
    setCoverToImageView(ivCover, book)
    rbRating.rating = book.rating
    switchIsEl.isChecked = book.isDigital

    if (hintStyle) {
        etTitle.hint = book.title
        etAuthor.hint = book.author
        etComment.hint = book.comments
    } else {
        etTitle.setText(book.title)
        etAuthor.setText(book.author)
        etComment.setText(book.comments)
    }
}

fun setBookInfoFieldsEnabled(enabled: Boolean, etTitle: EditText, etAuthor: EditText, rbRating: RatingBar,
                             switchIsEl: Switch, etComment: EditText) {
    etTitle.isEnabled = enabled
    etAuthor.isEnabled = enabled
    rbRating.isEnabled = enabled
    switchIsEl.isEnabled = enabled
    etComment.isEnabled = enabled
}

fun setBorrowedBookSpecialFieldsEnabled(enabled: Boolean, etOwner: EditText,
                                        dpReceiveDate: DatePicker, dpReturnDate: DatePicker) {
    etOwner.isEnabled = enabled
    dpReceiveDate.isEnabled = enabled
    dpReturnDate.isEnabled = enabled
}

fun setLendedBookSpecialFieldsEnabled(enabled: Boolean, etRecipient: EditText,
                                      dpTransferDate: DatePicker, dpReturnDate: DatePicker) {
    etRecipient.isEnabled = enabled
    dpReturnDate.isEnabled = enabled
    dpTransferDate.isEnabled = enabled
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