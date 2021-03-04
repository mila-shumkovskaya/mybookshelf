package com.study.mybookshelf

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.widget.*
import androidx.core.graphics.drawable.toBitmap
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import com.study.mybookshelf.R
import java.time.LocalDate
import androidx.fragment.app.Fragment
import com.study.mybookshelf.model.BorrowedBook
import com.study.mybookshelf.utils.*
import org.mockito.Mockito
import org.mockito.Mockito.mock
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}

//class FragmentUtilsUnitTest {
//
//    private val returnDate = LocalDate.of(2020, 12, 20)
//    private val receiveDate = LocalDate.of(2019, 12, 10) //.toString()
//
//    private val borrowedBook  = BorrowedBook(0, "title", "author", ByteArray(0), 5.0.toFloat(),
//        true, "comment", "owner", returnDate.toString(), receiveDate.toString())
////    private val title = "title"
////    private val author = "author"
////    private val cover = ByteArray(0)
////    private val rating = 5.0.toFloat()
////    private val isElectronic = true
////    private val comment = "comment"
////    private val owner = "owner"
//
//    private val context = mock(Context::class.java)
//    private val ivCover = ImageView(context)
//    private val etTitle = EditText(context)
//    private val etAuthor = EditText(context)
//    private val rbRating = RatingBar(context)
//    private val switchIsEl = Switch(context)
//    private val etComment = EditText(context)
//    private val etOwner = EditText(context)
//    private val cReceiveDate: Calendar = Calendar.getInstance()
//    private val cReturnDate: Calendar = Calendar.getInstance()
//
//    @Before
//    fun setUpFields() {
////        ivCover.setImageResource(R.mipmap.book_cover_foreground)
//        //val mockBitmapFactory = mock(BitmapFactory::class.java)
//        val mockBitmap = mock(Bitmap::class.java)
//        //val bitmap = BitmapFactory.decodeResource(context.resources, R.mipmap.book_cover_foreground)
//        //val bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888)
//        //bitmap.eraseColor(Color.GREEN)
//        ivCover.setImageBitmap(mockBitmap.resize())
//        etTitle.setText(borrowedBook.title)
//        etAuthor.setText(borrowedBook.author)
//        rbRating.rating = borrowedBook.rating
//        switchIsEl.isChecked = borrowedBook.isDigital
//        etComment.setText(borrowedBook.comments)
//        etOwner.setText(borrowedBook.owner)
//
//        cReceiveDate.set(Calendar.YEAR, receiveDate.year)
//        cReceiveDate.set(Calendar.MONTH, receiveDate.monthValue)
//        cReceiveDate.set(Calendar.DAY_OF_MONTH, receiveDate.dayOfMonth)
//
//        cReturnDate.set(Calendar.YEAR, returnDate.year)
//        cReturnDate.set(Calendar.MONTH, returnDate.monthValue)
//        cReturnDate.set(Calendar.DAY_OF_MONTH, returnDate.dayOfMonth)
//
//        //borrowedBook.photo = mockBitmap.resize()!!.toByteArray()
//    }
//
//    @Test
//    fun getCorrectInfoFromFields() {
//        val book = getBorrowedBookFromFields(etTitle, etAuthor, ivCover, rbRating, switchIsEl,
//            etComment, etOwner, cReceiveDate, cReturnDate)
//        assertEquals(borrowedBook, book)
//    }
//}