package com.study.mybookshelf

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.widget.*
import com.study.mybookshelf.model.BorrowedBook
import com.study.mybookshelf.model.LendedBook
import com.study.mybookshelf.model.LibraryBook
import com.study.mybookshelf.utils.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

//@RunWith(AndroidJUnit4::class)
class FragmentUtilsInstrumentedTest {

    private val returnDate = "20.12.2020"
    private val receiveDate = "10.12.2019"
    private val transferDate = "15.07.2017"

    private val libraryBook  = LibraryBook(0, "title", "author", ByteArray(0), 5.0.toFloat(),
        true, "comment")
    private val borrowedBook  = BorrowedBook(0, "title", "author", ByteArray(0), 5.0.toFloat(),
        true, "comment", "owner", returnDate, receiveDate)
    private val lendedBook  = LendedBook(0, "title", "author", ByteArray(0), 5.0.toFloat(),
        true, "comment", "recipient", returnDate, transferDate)

    private val ivCover = mock(ImageView::class.java)
    private val etTitle = mock(EditText::class.java)
    private val etAuthor = mock(EditText::class.java)
    private val rbRating = mock(RatingBar::class.java)
    private val switchIsEl = mock(Switch::class.java)
    private val etComment = mock(EditText::class.java)

    private val etOwner = mock(EditText::class.java)
    private val cReceiveDate: Calendar = Calendar.getInstance()
    private val cReturnDate: Calendar = Calendar.getInstance()

    private val etRecipient = mock(EditText::class.java)
    private val cTransferDate: Calendar = Calendar.getInstance()

    private val dateFormat = "dd.MM.yyyy"
    private val sdf = SimpleDateFormat(dateFormat, Locale.US)

    @Before
    fun setUpFields() {
    // book fields
//      Mockito.doCallRealMethod().`when`(etTitle).setText(borrowedBook.title)
        Mockito.doReturn(borrowedBook.title.toEditable()).`when`(etTitle).text
        Mockito.doReturn(borrowedBook.author.toEditable()).`when`(etAuthor).text
        Mockito.doReturn(borrowedBook.rating).`when`(rbRating).rating
        Mockito.doReturn(borrowedBook.isDigital).`when`(switchIsEl).isChecked
        Mockito.doReturn(borrowedBook.comments.toEditable()).`when`(etComment).text

        val bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888)
//        bitmap.eraseColor(Color.GREEN)
        val drawable: Drawable = BitmapDrawable(bitmap.resize())
        Mockito.doReturn(drawable).`when`(ivCover).drawable
        ivCover.setImageBitmap(bitmap.resize())

    // special borrowedBook fields
        Mockito.doReturn(borrowedBook.owner.toEditable()).`when`(etOwner).text
        cReceiveDate.time = sdf.parse(borrowedBook.receiveDate)
        cReturnDate.time = sdf.parse(borrowedBook.returnDate)

    // special lendedBook fields
        Mockito.doReturn(lendedBook.recipient.toEditable()).`when`(etRecipient).text
        cTransferDate.time = sdf.parse(lendedBook.transferDate)

    // make book photos from converted new bitmap
        libraryBook.photo = bitmap.resize()!!.toByteArray()
        borrowedBook.photo = bitmap.resize()!!.toByteArray()
        lendedBook.photo = bitmap.resize()!!.toByteArray()
    }

    @Test
    fun testGetLibraryBookFromFields() {
        val book = getLibraryBookFromFields(etTitle, etAuthor, ivCover, rbRating, switchIsEl,
            etComment)
        assertEquals(borrowedBook.title, book.title)
        assertEquals(borrowedBook.author, book.author)
        assertTrue(borrowedBook.photo.contentEquals(book.photo))
        assertEquals(borrowedBook.rating, book.rating)
        assertEquals(borrowedBook.isDigital, book.isDigital)
        assertEquals(borrowedBook.comments, book.comments)
    }

    @Test
    fun testGetBorrowedBookFromFields() {
        val book = getBorrowedBookFromFields(etTitle, etAuthor, ivCover, rbRating, switchIsEl,
            etComment, etOwner, cReceiveDate, cReturnDate)
        assertEquals(borrowedBook.title, book.title)
        assertEquals(borrowedBook.author, book.author)
        assertTrue(borrowedBook.photo.contentEquals(book.photo))
        assertEquals(borrowedBook.rating, book.rating)
        assertEquals(borrowedBook.isDigital, book.isDigital)
        assertEquals(borrowedBook.comments, book.comments)
        assertEquals(borrowedBook.owner, book.owner)

        assertEquals(borrowedBook.receiveDate, book.receiveDate)
        assertEquals(borrowedBook.returnDate, book.returnDate)
    }

    @Test
    fun testGetLendedBookFromFields() {
        val book = getLendedBookFromFields(etTitle, etAuthor, ivCover, rbRating, switchIsEl,
            etComment, etRecipient, cTransferDate, cReturnDate)
        assertEquals(lendedBook.title, book.title)
        assertEquals(lendedBook.author, book.author)
        assertTrue(lendedBook.photo.contentEquals(book.photo))
        assertEquals(lendedBook.rating, book.rating)
        assertEquals(lendedBook.isDigital, book.isDigital)
        assertEquals(lendedBook.comments, book.comments)
        assertEquals(lendedBook.recipient, book.recipient)

        //val fieldSdf = SimpleDateFormat("dd.MM.yyyy", Locale.US)
        //assertEquals(sdf.parse(lendedBook.transferDate), fieldSdf.parse(book.transferDate))
        assertEquals(lendedBook.transferDate, book.transferDate)
        //assertEquals(sdf.parse(lendedBook.returnDate), fieldSdf.parse(book.returnDate))
        assertEquals(lendedBook.returnDate, book.returnDate)
    }


    // MAYBE THERE'S NO NEED IN THIS
//    @Test
//    fun testSetBookInfoFieldsEnabled() {
//        setBookInfoFieldsEnabled(false, etTitle, etAuthor, rbRating, switchIsEl, etComment)
//        assertFalse(etTitle.isEnabled)
//        assertFalse(etAuthor.isEnabled)
//        assertFalse(rbRating.isEnabled)
//        assertFalse(switchIsEl.isEnabled)
//        assertFalse(etComment.isEnabled)
//
//        setBookInfoFieldsEnabled(true, etTitle, etAuthor, rbRating, switchIsEl, etComment)
//        assertTrue(etTitle.isEnabled)
//        assertTrue(etAuthor.isEnabled)
//        assertTrue(rbRating.isEnabled)
//        assertTrue(switchIsEl.isEnabled)
//        assertTrue(etComment.isEnabled)
//    }
//
//    @Test
//    fun testSetBorrowedBookSpecialFieldsEnabled() {
//        setBorrowedBookSpecialFieldsEnabled(false, etOwner, dpReceiveDate, dpReturnDate)
//        assertFalse(etOwner.isEnabled)
//        assertFalse(dpReceiveDate.isEnabled)
//        assertFalse(dpReturnDate.isEnabled)
//
//        setBorrowedBookSpecialFieldsEnabled(true, etOwner, dpReceiveDate, dpReturnDate)
//        assertTrue(etOwner.isEnabled)
//        assertTrue(dpReceiveDate.isEnabled)
//        assertTrue(dpReturnDate.isEnabled)
//    }
//
//    @Test
//    fun testSetLendedBookSpecialFieldsEnabled() {
//        setLendedBookSpecialFieldsEnabled(false, etRecipient, dpTransferDate, dpReturnDate)
//        assertFalse(etRecipient.isEnabled)
//        assertFalse(dpTransferDate.isEnabled)
//        assertFalse(dpReturnDate.isEnabled)
//
//        setLendedBookSpecialFieldsEnabled(true, etRecipient, dpTransferDate, dpReturnDate)
//        assertTrue(etRecipient.isEnabled)
//        assertTrue(dpTransferDate.isEnabled)
//        assertTrue(dpReturnDate.isEnabled)
//    }
}