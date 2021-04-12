package com.study.mybookshelf.integration_tests


import android.os.Handler
import android.os.HandlerThread
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.study.mybookshelf.DetailsActivity
import com.study.mybookshelf.model.BorrowedBook
import com.study.mybookshelf.model.LibraryBook
import com.study.mybookshelf.ui.LibraryViewModel
import com.study.mybookshelf.utils.getString
import com.study.mybookshelf.view_models.BorrowedBooksViewModel
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmResults
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalDate
import java.util.concurrent.CountDownLatch


@LargeTest
@RunWith(AndroidJUnit4::class)
class BorrowedBooksInDatabaseTest {

    @Rule
    @JvmField
    val detailsActivityTestRule = ActivityTestRule(DetailsActivity::class.java)

    @Before
    fun init() {
        Realm.init(detailsActivityTestRule.activity)
        val testConfig = RealmConfiguration.Builder().inMemory().name("test-realm").build()
        Realm.setDefaultConfiguration(testConfig)
    }




    @Test
    fun addBookTest() {
        Realm.init(detailsActivityTestRule.activity)
        val testConfig = RealmConfiguration.Builder().inMemory().name("test-realm").build()
        Realm.setDefaultConfiguration(testConfig);

        val thread = HandlerThread("test")
        thread.start()
        val handler = Handler(thread.looper)
        val latch = CountDownLatch(1)
        handler.post {
            val realm = Realm.getDefaultInstance()
            val borrowedBooksViewModel = BorrowedBooksViewModel()
            val book = BorrowedBook(0, "test_title", "test_author", ByteArray(0), 5.0.toFloat(), true, "comment",
                "Ted", LocalDate.of(2019, 12, 10).getString(), LocalDate.of(2020, 12, 20).getString())
            borrowedBooksViewModel.createOrUpdateBook(book)

            val results: RealmResults<BorrowedBook>? = realm.where(BorrowedBook::class.java).equalTo("id", book.id).equalTo("title", book.title).equalTo("author", book.author).findAll()
            assertEquals(1, results!!.size)
            realm.close()
            latch.countDown()
        }
        latch.await()
    }

    @Test
    fun updateBookTest() {
        val thread = HandlerThread("test")
        thread.start()
        val handler = Handler(thread.looper)
        val latch = CountDownLatch(1)
        handler.post {
            val realm = Realm.getDefaultInstance()
            val borrowedBooksViewModel = BorrowedBooksViewModel()
            val book = BorrowedBook(0, "test_title", "test_author", ByteArray(0), 5.0.toFloat(), true, "comment",
                "Ted", LocalDate.of(2019, 12, 10).getString(), LocalDate.of(2020, 12, 20).getString())
            borrowedBooksViewModel.createOrUpdateBook(book)

            val newBook = BorrowedBook(0, "new_title", "new_author", ByteArray(0), 5.0.toFloat(), true, "comment",
                "Ted", LocalDate.of(2019, 12, 10).getString(), LocalDate.of(2020, 12, 20).getString())
            borrowedBooksViewModel.createOrUpdateBook(newBook)
            borrowedBooksViewModel.createOrUpdateBook(newBook)
            val results: RealmResults<BorrowedBook>? = realm.where(BorrowedBook::class.java).equalTo("id", book.id).equalTo("title", book.title).equalTo("author", book.author).findAll()
            val newResults: RealmResults<BorrowedBook>? = realm.where(BorrowedBook::class.java).equalTo("id", newBook.id).equalTo("title", newBook.title).equalTo("author", newBook.author).findAll()
            assertEquals(0, results!!.size)
            assertEquals(1, newResults!!.size)
            realm.close()
            latch.countDown()
        }
        latch.await()
    }

    @Test
    fun deleteBookTest() {
        val thread = HandlerThread("test")
        thread.start()
        val handler = Handler(thread.looper)
        val latch = CountDownLatch(1)
        handler.post {
            val realm = Realm.getDefaultInstance()
            val borrowedBooksViewModel = BorrowedBooksViewModel()
            val book = BorrowedBook(0, "test_title", "test_author", ByteArray(0), 5.0.toFloat(), true, "comment",
                "Ted", LocalDate.of(2019, 12, 10).getString(), LocalDate.of(2020, 12, 20).getString())
            borrowedBooksViewModel.createOrUpdateBook(book)
            borrowedBooksViewModel.deleteBook(book)
            val results: RealmResults<BorrowedBook>? = realm.where(BorrowedBook::class.java).equalTo("id", book.id).findAll()
            assertEquals(0, results!!.size)
            realm.close()
            latch.countDown()
        }
        latch.await()
    }
}