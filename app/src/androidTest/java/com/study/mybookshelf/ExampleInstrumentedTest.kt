package com.study.mybookshelf

import android.app.Activity
import android.app.Instrumentation
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.widget.EditText
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.Switch
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.res.TypedArrayUtils.getString
import androidx.core.graphics.drawable.toBitmap
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.study.mybookshelf.model.BorrowedBook
import com.study.mybookshelf.model.LendedBook
import com.study.mybookshelf.ui.BorrowedBookDetailsFragment
import com.study.mybookshelf.ui.SharedPreferencesId
import com.study.mybookshelf.utils.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
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
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.study.mybookshelf", appContext.packageName)
    }
}

//@RunWith(AndroidJUnit4::class)
//class CorrectBorrowedBookInfoFromFragmentFieldsTest {
//    @get:Rule
//    var activityRule: ActivityTestRule<DetailsActivity> = ActivityTestRule<DetailsActivity>(DetailsActivity::class.java)
//
//    @Before
//    fun setUpFragment() {
//        val supportFragmentManager = activityRule.activity.supportFragmentManager
//        supportFragmentManager.beginTransaction()
//            .add(R.id.container, BorrowedBookDetailsFragment())
//            .commit()
//        //supportFragmentManager.fragments[0]
//        //val root = inflater.inflate(R.layout.fragment_borrowed_book_details, container, false)
//    }
//
//    @Test
//    fun getCorrectInfoFromFields_title() {
//        //val scenario = launchFragmentInContainer<BorrowedBookDetailsFragment>()
//        val titleToType = "snail on the slope"
//        onView(withId(R.id.et_title)).perform(clearText(), typeText(titleToType), closeSoftKeyboard())
//        val fragment = activityRule.activity.supportFragmentManager.fragments[0] as BorrowedBookDetailsFragment
//        assertEquals(titleToType, fragment.getInfoFromFields().title)
//    }
//}

//@RunWith(AndroidJUnit4::class)
//class CorrectBorrowedBookInfoFromFragmentFieldsTest {
//    @get:Rule
//    var intentTestRule: IntentsTestRule<DetailsActivity> = IntentsTestRule<DetailsActivity>(DetailsActivity::class.java)
//
//    @Before
//    fun setUpFragment() {
//        val intent = Intent()
//        val book  = BorrowedBook(1, "title", "author", ByteArray(0), 5.0.toFloat(), true, "comment",
//            "owner", LocalDate.of(2019, 12, 10).getString(), LocalDate.of(2020, 12, 20).getString())
//        intent.putExtra("book", book)
//        val result = Instrumentation.ActivityResult(Activity.RESULT_OK, intent)
//        //intending(toPackage("com.study.mybookshelf")).respondWith(result)
//        launchActivity<DetailsActivity>()
//
//        val supportFragmentManager = intentTestRule.activity.supportFragmentManager
//        supportFragmentManager.beginTransaction()
//            .add(R.id.container, BorrowedBookDetailsFragment())
//            .commit()
//        //supportFragmentManager.fragments[0].context
//
//        //val root = inflater.inflate(R.layout.fragment_borrowed_book_details, container, false)
//    }
//
//    @Test
//    fun getCorrectInfoFromFields_title() {
//        //val scenario = launchFragmentInContainer<BorrowedBookDetailsFragment>()
//        val titleToType = "snail on the slope"
//        onView(withId(R.id.et_title)).perform(clearText(), typeText(titleToType), closeSoftKeyboard())
//        val fragment = intentTestRule.activity.supportFragmentManager.fragments[0] as BorrowedBookDetailsFragment
//        assertEquals(titleToType, fragment.getInfoFromFields().title)
//    }
//}

//@RunWith(AndroidJUnit4::class)
class FragmentUtilsUnitTest {
    val context = InstrumentationRegistry.getInstrumentation().targetContext

    private val returnDate = LocalDate.of(2020, 12, 20)
    private val receiveDate = LocalDate.of(2019, 12, 10)
    private val transferDate = LocalDate.of(2017, 7, 15)

    private val borrowedBook  = BorrowedBook(0, "title", "author", ByteArray(0), 5.0.toFloat(),
        true, "comment", "owner", returnDate.toString(), receiveDate.toString())
    private val lendedBook  = LendedBook(0, "title", "author", ByteArray(0), 5.0.toFloat(),
        true, "comment", "recipient", returnDate.toString(), transferDate.toString())
//    private val title = "title"
//    private val author = "author"
//    private val cover = ByteArray(0)
//    private val rating = 5.0.toFloat()
//    private val isElectronic = true
//    private val comment = "comment"
//    private val owner = "owner"

    //private val context = Mockito.mock(Context::class.java)
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

    private val dateFormat = "yyyy-MM-dd"
    private val sdf = SimpleDateFormat(dateFormat, Locale.US)

    @Before
    fun setUpFields() {

//        Mockito.doCallRealMethod().`when`(etTitle).setText(borrowedBook.title)
//        Mockito.doCallRealMethod().`when`(etTitle).text
        Mockito.doReturn(borrowedBook.title.toEditable()).`when`(etTitle).text
//        etTitle.setText(borrowedBook.title)

//        Mockito.doCallRealMethod().`when`(etAuthor).setText(borrowedBook.author)
        Mockito.doReturn(borrowedBook.author.toEditable()).`when`(etAuthor).text
//        etAuthor.setText(borrowedBook.author)

        Mockito.doReturn(borrowedBook.rating).`when`(rbRating).rating
//        rbRating.rating = borrowedBook.rating

        Mockito.doReturn(borrowedBook.isDigital).`when`(switchIsEl).isChecked
//        switchIsEl.isChecked = borrowedBook.isDigital

//        Mockito.doCallRealMethod().`when`(etComment).setText(borrowedBook.comments)
        Mockito.doReturn(borrowedBook.comments.toEditable()).`when`(etComment).text
//        etComment.setText(borrowedBook.comments)

//        Mockito.doCallRealMethod().`when`(etOwner).setText(borrowedBook.owner)
        Mockito.doReturn(borrowedBook.owner.toEditable()).`when`(etOwner).text
//        etOwner.setText(borrowedBook.owner)

    //    ivCover.setImageResource(R.mipmap.book_cover_foreground)
        //val mockBitmapFactory = mock(BitmapFactory::class.java)
        //val mockBitmap = Mockito.mock(Bitmap::class.java)
        //val bitmap = BitmapFactory.decodeResource(context.resources, R.mipmap.book_cover_foreground)
        val bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888)
        //bitmap.eraseColor(Color.GREEN)
        //ivCover.setImageBitmap(mockBitmap.resize())
    //    ivCover.setImageBitmap(ivCover.drawable.toBitmap().resize())
        val drawable: Drawable = BitmapDrawable(bitmap.resize())
        Mockito.doReturn(drawable).`when`(ivCover).drawable
        ivCover.setImageBitmap(bitmap.resize())

        cReceiveDate.time = sdf.parse(borrowedBook.receiveDate)
//        cReceiveDate.set(Calendar.YEAR, receiveDate.year)
//        cReceiveDate.set(Calendar.MONTH, receiveDate.monthValue)
//        cReceiveDate.set(Calendar.DAY_OF_MONTH, receiveDate.dayOfMonth)

        cReturnDate.time = sdf.parse(borrowedBook.returnDate)
//        cReturnDate.set(Calendar.YEAR, returnDate.year)
//        cReturnDate.set(Calendar.MONTH, returnDate.monthValue)
//        cReturnDate.set(Calendar.DAY_OF_MONTH, returnDate.dayOfMonth)

        Mockito.doReturn(lendedBook.recipient.toEditable()).`when`(etRecipient).text

        cTransferDate.time = sdf.parse(lendedBook.transferDate)

        borrowedBook.photo = bitmap.resize()!!.toByteArray()
        lendedBook.photo = bitmap.resize()!!.toByteArray()
    }

    @Test
    fun getBorrowedBookFromFields() {
        val book = getBorrowedBookFromFields(etTitle, etAuthor, ivCover, rbRating, switchIsEl,
            etComment, etOwner, cReceiveDate, cReturnDate)
        assertEquals(borrowedBook.title, book.title)
        assertEquals(borrowedBook.author, book.author)
        assertTrue(borrowedBook.photo.contentEquals(book.photo))
        assertEquals(borrowedBook.rating, book.rating)
        assertEquals(borrowedBook.isDigital, book.isDigital)
        assertEquals(borrowedBook.comments, book.comments)
        assertEquals(borrowedBook.owner, book.owner)

        val field_sdf = SimpleDateFormat("dd.MM.yyyy", Locale.US)
        assertEquals(sdf.parse(borrowedBook.receiveDate), field_sdf.parse(book.receiveDate))
        assertEquals(sdf.parse(borrowedBook.returnDate), field_sdf.parse(book.returnDate))
    }

    @Test
    fun getLendedBookFromFields() {
        val book = getLendedBookFromFields(etTitle, etAuthor, ivCover, rbRating, switchIsEl,
            etComment, etRecipient, cTransferDate, cReturnDate)
        assertEquals(lendedBook.title, book.title)
        assertEquals(lendedBook.author, book.author)
        assertTrue(lendedBook.photo.contentEquals(book.photo))
        assertEquals(lendedBook.rating, book.rating)
        assertEquals(lendedBook.isDigital, book.isDigital)
        assertEquals(lendedBook.comments, book.comments)
        assertEquals(lendedBook.recipient, book.recipient)

        val field_sdf = SimpleDateFormat("dd.MM.yyyy", Locale.US)
        assertEquals(sdf.parse(lendedBook.transferDate), field_sdf.parse(book.transferDate))
        assertEquals(sdf.parse(lendedBook.returnDate), field_sdf.parse(book.returnDate))
    }
}